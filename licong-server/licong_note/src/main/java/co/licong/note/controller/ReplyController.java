package co.licong.note.controller;

import co.licong.note.pojo.Reply;
import co.licong.note.service.ReplyService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部回复
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", replyService.findAll());
    }

    /**
     * 根据文章ID查询回复
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findByNoteId(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", replyService.findByNoteId(id));
    }

    /**
     * 添加回复
     * @param reply
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Reply reply) {
        if (reply.getNickname() == null || reply.getNickname() == "undefined") {
            return new Result(false, StatusCode.ERROR, "昵称不能为空");
        }
        if (StringUtils.isEmpty(reply.getNickname()) || StringUtils.isEmpty(reply.getContent())) {
            return new Result(false, StatusCode.ERROR, "昵称或评论为空");
        }
        replyService.add(reply);
        return new Result(true, StatusCode.OK, "评论成功");
    }

}

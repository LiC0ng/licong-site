package co.licong.talk.controller;

import co.licong.talk.pojo.Talk;
import co.licong.talk.service.TalkService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("talk")
public class TalkController {

    @Autowired
    private TalkService talkService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部回复
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", talkService.findAll());
    }

    /**
     * 添加回复
     *
     * @param talk
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Talk talk) {
        if (talk.getNickname() == null || talk.getNickname() == "undefined") {
            return new Result(false, StatusCode.ERROR, "昵称不能为空");
        }
        talkService.save(talk);
        return new Result(true, StatusCode.OK, "留言成功");
    }

    /**
     * 修改
     *
     * @param talk
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Talk talk) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
        }
        talkService.update(talk);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除回复
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result update(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
        }
        talkService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}

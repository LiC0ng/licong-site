package co.licong.memo.controller;

import co.licong.memo.pojo.Memo;
import co.licong.memo.service.MemoService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 备忘录控制层
 */
@RestController
@CrossOrigin
@RequestMapping("memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询所有备忘录
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAdd() {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        return new Result(true, StatusCode.OK, "查询成功", memoService.findAll(claims.getId()));
    }

    /**
     * 增加或修改备忘录
     *
     * @param memo
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Memo memo) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        memo.setUserid(claims.getId());
        memoService.add(memo);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 删除备忘录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        memoService.deleteById(id, claims.getId());
        return new Result(true, StatusCode.OK, "删除成功");
    }
}

package co.licong.password.controller;

import co.licong.password.pojo.Password;
import co.licong.password.service.PasswordService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 密码簿控制层
 */
@RestController
@CrossOrigin
@RequestMapping("password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询所有密码簿
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            System.out.println("1");
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        return new Result(true, StatusCode.OK, "查询成功", passwordService.findAll(claims.getId()));
    }

    /**
     * 增加或修改密码簿
     *
     * @param password
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Password password) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        password.setUserid(claims.getId());
        passwordService.add(password);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 删除密码簿
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
        passwordService.deleteById(id, claims.getId());
        return new Result(true, StatusCode.OK, "删除成功");
    }
}

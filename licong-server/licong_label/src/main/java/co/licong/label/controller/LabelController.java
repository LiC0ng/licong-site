package co.licong.label.controller;

import co.licong.label.pojo.Label;
import co.licong.label.service.LabelService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 标签控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询所有标签
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAdd() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 根据Id查询标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * 查询关联标签列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/labels/{id}", method = RequestMethod.GET)
    public Result findLabels(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findLabels(id));
    }

    /**
     * 查询根标签列表
     *
     * @return
     */
    @RequestMapping(value = "/labels", method = RequestMethod.GET)
    public Result findRootLabels() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findRootLabels());
    }

    /**
     * 增加标签
     *
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
        }
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改标签
     *
     * @param label
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
        }
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.ACCESSERROR, "无权访问");
        }
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 查询子标签
     *
     * @param id 父标签Id
     * @return
     */
    @RequestMapping(value = "/sub/{id}", method = RequestMethod.GET)
    public Result findSubLabels(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findSubLabels(id));
    }

    @RequestMapping(value = "/incount/{id}", method = RequestMethod.PUT)
    public Result increaseCount(@PathVariable String id) {
        labelService.increaseCount(id);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/decount/{id}", method = RequestMethod.PUT)
    public Result decreaseCount(@PathVariable String id) {
        labelService.decreaseCount(id);
        return new Result(true, StatusCode.OK, "更新成功");
    }
}

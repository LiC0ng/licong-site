package co.licong.note.controller;

import co.licong.note.pojo.Note;
import co.licong.note.service.NoteService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 */
@RestController
@CrossOrigin
@RequestMapping("note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部笔记数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        return new Result(true, StatusCode.OK, "查询成功", noteService.findAll());
    }

    /**
     * 查询全部公开笔记数据
     *
     * @return
     */
    @RequestMapping(value = "/public/{page}/{size}", method = RequestMethod.GET)
    public Result findAllPublic(@PathVariable int page, @PathVariable int size) {
        Page<Note> pageList = noteService.findAllPublic(page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Note>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.OK, "查询成功", noteService.findNotePublic(id));
        }
        return new Result(true, StatusCode.OK, "查询成功", noteService.findById(id));
    }

    /**
     * 根据标签Id查询文章列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
    public Result findMemoList(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(true, StatusCode.OK, "查询成功", noteService.findPublicNotes(id));
        }
        return new Result(true, StatusCode.OK, "查询成功", noteService.findByLabelid(id));
    }

    /**
     * 增加
     *
     * @param note
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Note note) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        if (StringUtils.isEmpty(note.getTitle()) || StringUtils.isEmpty(note.getTitle())
                || StringUtils.isEmpty(note.getIspublic()) || StringUtils.isEmpty(note.getId())) {
            return new Result(false, StatusCode.ERROR, "保存失败，请确保某项内容不为空");
        }
        return new Result(true, StatusCode.OK, "增加成功", noteService.add(note));
    }

    /**
     * 修改
     *
     * @param note
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Note note) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        if (StringUtils.isEmpty(note.getTitle()) || StringUtils.isEmpty(note.getTitle()) || StringUtils.isEmpty(note.getIspublic())) {
            return new Result(false, StatusCode.ERROR, "保存失败，请确保某项内容不为空");
        }
        noteService.update(note);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        noteService.deleteByIdAndUserid(id, claims.getId());
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 获取一个新的笔记Id
     *
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Result getNewId() {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        return new Result(true, StatusCode.OK, "获取ID成功", noteService.getNewId());
    }
}
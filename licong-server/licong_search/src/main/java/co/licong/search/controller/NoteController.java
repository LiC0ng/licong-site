package co.licong.search.controller;

import co.licong.search.pojo.Note;
import co.licong.search.service.NoteSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteSearchService noteSearchService;

    @PostMapping
    public Result save(@RequestBody Note note) {
        noteSearchService.add(note);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @GetMapping("{key}/{page}/{size}")
    public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
        Page<Note> pageData = noteSearchService.findByKey(key, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Note>(pageData.getTotalElements(), pageData.getContent()));
    }

}

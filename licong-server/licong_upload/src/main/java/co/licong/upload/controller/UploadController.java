package co.licong.upload.controller;

import co.licong.upload.service.ImageService;
import co.licong.upload.service.UploadService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件控制层
 */
@RestController
@CrossOrigin
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询所有文件
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAdd() {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        return new Result(true, StatusCode.OK, "查询成功", uploadService.findAll(claims.getId()));
    }

    /**
     * 增加或修改文件
     *
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(MultipartFile file) throws IOException {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        if (file.isEmpty()) {
            return new Result(false, StatusCode.ERROR, "文件不能为空");
        }
        uploadService.add(file, claims.getId());
        return new Result(true, StatusCode.OK, "上传成功");
    }

    /**
     * 删除文件
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
        uploadService.deleteById(id, claims.getId());
        return new Result(true, StatusCode.OK, "删除成功");
    }
}

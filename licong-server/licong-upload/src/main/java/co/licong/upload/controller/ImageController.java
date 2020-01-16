package co.licong.upload.controller;

import co.licong.upload.service.ImageService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图片控制层
 */
@RestController
@CrossOrigin
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Result uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String id) throws IOException {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        if (StringUtils.isEmpty(id)) {
            return new Result(false, StatusCode.ERROR, "笔记id不能为空");
        }
        if (file.isEmpty()) {
            return new Result(false, StatusCode.ERROR, "文件不能为空");
        }
        BufferedImage bi = ImageIO.read(file.getInputStream());
        if(bi == null){
            return new Result(false, StatusCode.ERROR, "上传的不是图片");
        }
        return new Result(true, StatusCode.OK, "上传成功", imageService.uploadImage(file, id, claims.getId()));
    }

    /**
     * 删除笔记内的图片
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteImageById(@PathVariable String id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        if (StringUtils.isEmpty(id)) {
            return new Result(false, StatusCode.ERROR, "笔记id不能为空");
        }
        imageService.deleteImageByNoteid(id, claims.getId());
        return new Result(true, StatusCode.OK, "删除成功");
    }
}

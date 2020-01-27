package co.licong.upload.service;

import co.licong.upload.client.FastDFSClientWrapper;
import co.licong.upload.dao.ImageDao;
import co.licong.upload.pojo.NoteImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 笔记图片业务逻辑类
 */
@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private FastDFSClientWrapper client;

    /**
     * 删除笔记内的图片
     * @param noteid
     * @param userid
     */
    public void deleteImageByNoteid(String noteid, String userid) {
        List<NoteImage> noteImageList = imageDao.findByNoteidAndUserid(noteid, userid);
        for (NoteImage noteImage : noteImageList) {
            client.deleteFile(noteImage.getPath());
        }
        imageDao.deleteByNoteidAndUserid(noteid, userid);
    }

    /**
     * 上传图片文件
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file, String noteid, String userid) throws IOException {
        String path = client.uploadFile(file);
        NoteImage noteImage = new NoteImage();
        noteImage.setNoteid(noteid);
        noteImage.setUserid(userid);
        noteImage.setPath(path);
        imageDao.save(noteImage);
        return path;
    }
}

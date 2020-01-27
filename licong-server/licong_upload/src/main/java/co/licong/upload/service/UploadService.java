package co.licong.upload.service;

import co.licong.upload.client.FastDFSClientWrapper;
import co.licong.upload.dao.UploadDao;
import co.licong.upload.pojo.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import util.IdWorker;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 文件业务逻辑类
 */
@Service
@Transactional
public class UploadService {

    @Autowired
    private UploadDao uploadDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private FastDFSClientWrapper client;

    /**
     * 查询全部文件
     *
     * @return
     */
    public List<Upload> findAll(String userid) {
        return uploadDao.findAllByUserid(userid);
    }

    /**
     * 上传文件
     * @param file
     * @param userId
     * @throws IOException
     */
    public void add(MultipartFile file, String userId) throws IOException {
        String path = client.uploadFile(file);
        Upload upload = new Upload();
        upload.setId(idWorker.nextId() + "");
        upload.setFilename(file.getOriginalFilename());
        upload.setFilesize(file.getSize());
        upload.setCreatetime(new Date());
        upload.setUserid(userId);
        upload.setFileurl(path);
        uploadDao.save(upload);
    }

    /**
     * 删除文件
     *
     * @param id
     */
    public void deleteById(String id, String userid) {
        Upload upload = uploadDao.findByIdAndUserid(id, userid);
        client.deleteFile(upload.getFileurl());
        uploadDao.deleteByIdAndUserid(id, userid);
    }
}

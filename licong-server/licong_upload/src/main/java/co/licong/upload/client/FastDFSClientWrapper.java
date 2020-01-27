package co.licong.upload.client;

import java.io.IOException;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.service.FastFileStorageClient;

@Component
public class FastDFSClientWrapper {

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * @param file 文件对象
     * @return 文件路径
     * @throws IOException String
     * @Description: 上传文件
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * @param filePath 文件访问地址
     * @Description: 根据文件地址删除文件
     */
    public void deleteFile(String filePath) {
        StorePath storePath = StorePath.parseFromUrl(filePath);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }
}
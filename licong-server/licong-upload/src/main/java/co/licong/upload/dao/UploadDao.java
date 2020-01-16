package co.licong.upload.dao;

import co.licong.upload.pojo.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 文件数据访问接口
 */
public interface UploadDao extends JpaRepository<Upload, String> {

    List<Upload> findAllByUserid(String userid);

    Upload findByIdAndUserid(String id, String userid);

    void deleteByIdAndUserid(String id, String userid);
}

package co.licong.upload.dao;

import co.licong.upload.pojo.NoteImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 文件数据访问接口
 */
public interface ImageDao extends JpaRepository<NoteImage, String> {

    void deleteByNoteidAndUserid(String noteid, String userid);

    List<NoteImage> findByNoteidAndUserid(String noteid, String userid);
}

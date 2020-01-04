package co.licong.label.dao;

import co.licong.label.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 标签数据访问接口
 */
public interface LabelDao extends JpaRepository<Label, String> {

    List<Label> findByParentid(String id);

    @Modifying
    @Query(value = "UPDATE tb_label SET count=count+? WHERE id=?", nativeQuery = true)
    void increaseCount(Long count, String id);

    @Modifying
    @Query(value = "UPDATE tb_label SET count=count-? WHERE id=?", nativeQuery = true)
    void decreaseCount(Long count, String id);

    @Query(value = "SELECT * FROM tb_label WHERE parentid IS NULL", nativeQuery = true)
    List<Label> findRootLables();

}

package co.licong.note.dao;

import co.licong.note.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 */
public interface ReplyDao extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {

    List<Reply> findByNoteidOrderByCreatetimeDesc(String id);

    void deleteByNoteid(String noteid);
}

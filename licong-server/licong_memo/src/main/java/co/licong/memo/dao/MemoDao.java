package co.licong.memo.dao;

import co.licong.memo.pojo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 备忘录数据访问接口
 */
public interface MemoDao extends JpaRepository<Memo, String> {

    List<Memo> findAllByUserid(String userid);

    Memo findByIdAndUserid(String id, String userid);

    void deleteByIdAndUserid(String id, String userid);
}

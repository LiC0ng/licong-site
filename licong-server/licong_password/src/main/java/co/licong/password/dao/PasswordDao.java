package co.licong.password.dao;

import co.licong.password.pojo.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 密码簿数据访问接口
 */
public interface PasswordDao extends JpaRepository<Password, String> {

    List<Password> findAllByUserid(String userid);

    void deleteByIdAndUserid(String id, String userid);
}

package co.licong.user.dao;

import co.licong.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AdminDao extends JpaRepository<Admin,String>,JpaSpecificationExecutor<Admin>{

    Admin findByLoginname(String loginName);

}

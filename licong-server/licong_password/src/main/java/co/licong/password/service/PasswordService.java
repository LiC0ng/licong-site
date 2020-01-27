package co.licong.password.service;

import co.licong.password.dao.PasswordDao;
import co.licong.password.pojo.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.List;

/**
 * 密码簿业务逻辑类
 */
@Service
@Transactional
public class PasswordService {

    @Autowired
    private PasswordDao passwordDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部密码簿
     *
     * @return
     */
    public List<Password> findAll(String userid) {
        return passwordDao.findAllByUserid(userid);
    }

    /**
     * 增加密码簿
     *
     * @param password
     */
    public void add(Password password) {
        if (StringUtils.isEmpty(password.getId())) {
            password.setId(idWorker.nextId() + "");    // 设置Id
        }
        passwordDao.save(password);
    }

    /**
     * 删除密码簿
     *
     * @param id
     */
    public void deleteById(String id, String userid) {
        passwordDao.deleteByIdAndUserid(id, userid);
    }
}

package co.licong.memo.service;

import co.licong.memo.dao.MemoDao;
import co.licong.memo.pojo.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.List;

/**
 * 备忘录业务逻辑类
 */
@Service
@Transactional
public class MemoService {

    @Autowired
    private MemoDao memoDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部备忘录
     *
     * @return
     */
    public List<Memo> findAll(String userid) {
        return memoDao.findAllByUserid(userid);
    }

    /**
     * 增加备忘录
     *
     * @param memo
     */
    public void add(Memo memo) {
        if (StringUtils.isEmpty(memo.getId())) {
            memo.setId(idWorker.nextId() + "");    // 设置Id
        }
        memoDao.save(memo);
    }

    /**
     * 删除备忘录
     *
     * @param id
     */
    public void deleteById(String id, String userid) {
        memoDao.deleteByIdAndUserid(id, userid);
    }
}

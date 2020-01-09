package co.licong.talk.service;

import co.licong.talk.dao.TalkDao;
import co.licong.talk.pojo.Talk;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 服务层
 */
@Service
@Transactional
public class TalkService {

    @Autowired
    private TalkDao talkDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部回复
     *
     * @return
     */
    public List<Talk> findAll() {
        return talkDao.findAll();
    }

    /**
     * 添加回复
     *
     * @param talk
     */
    public void save(Talk talk) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims != null) {
            talk.setUserid(claims.getId());
        }
        talk.set_id(idWorker.nextId() + "");
        talk.setCreatetime(new Date());
        talkDao.save(talk);
    }

    /**
     * 修改回复
     *
     * @param talk
     */
    public void update(Talk talk) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims != null) {
            talk.setUserid(claims.getId());
        }
        talkDao.save(talk);
    }

    /**
     * 删除回复
     *
     * @param id
     */
    public void deleteById(String id) {
        talkDao.deleteById(id);
    }
}

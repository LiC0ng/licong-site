package co.licong.note.service;

import co.licong.note.dao.NoteDao;
import co.licong.note.dao.ReplyDao;
import co.licong.note.pojo.Reply;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部回复
     *
     * @return
     */
    public List<Reply> findAll() {
        return replyDao.findAll();
    }

    /**
     * 根据文章id查询回复
     *
     * @return
     */
    public List<Reply> findByNoteId(String id) {
        return replyDao.findByNoteidOrderByCreatetimeDesc(id);
    }

    /**
     * 添加回复
     *
     * @param reply
     */
    public void add(Reply reply) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims != null) {
            reply.setUserid(claims.getId());
        }
        noteDao.increaseComment(reply.getNoteid());
        reply.setId(idWorker.nextId() + "");
        reply.setCreatetime(new Date());
        replyDao.save(reply);
    }
}

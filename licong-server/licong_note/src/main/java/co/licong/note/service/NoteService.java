package co.licong.note.service;

import co.licong.note.client.LabelClient;
import co.licong.note.client.SearchClient;
import co.licong.note.client.UploadClient;
import co.licong.note.dao.NoteDao;
import co.licong.note.dao.ReplyDao;
import co.licong.note.pojo.Note;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
@Transactional
public class NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private LabelClient labelClient;

    @Resource
    private UploadClient uploadClient;

    @Resource
    private SearchClient searchClient;

    /**
     * 查询全部笔记列表
     *
     * @return
     */
    public List<Note> findAll() {
        return noteDao.findAll();
    }

    /**
     * 查询全部公开笔记列表
     *
     * @return
     */
    public Page<Note> findAllPublic(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatetime");
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        return noteDao.findByIspublic("1", pageRequest);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Note findById(String id) {
        Note note = (Note) redisTemplate.opsForValue().get("note_" + id);
        if (note == null) {
            note = noteDao.findById(id).get();
            redisTemplate.opsForValue().set("note_" + id, note);
        }
        return note;
    }

    /**
     * 增加
     */
    public Note add(Note note) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims != null) {
            note.setUserid(claims.getId());
        }
        note.setCreatetime(new Date());
        note.setUpdatetime(new Date());
        note.setComment(0);
        note.setVisits(0);
        labelClient.increaseCount(note.getLabelid());
        return noteDao.save(note);
    }

    /**
     * 修改
     */
    public void update(Note note) {
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims != null) {
            note.setUserid(claims.getId());
        }
        note.setUpdatetime(new Date());
        redisTemplate.delete("note_" + note.getId());
        noteDao.save(note);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteByIdAndUserid(String id, String userid) {
        Note note = noteDao.findById(id).get();
        labelClient.decreaseCount(note.getLabelid());
        redisTemplate.delete("note_" + id);
        noteDao.deleteByIdAndUserid(id, userid);
        replyDao.deleteByNoteid(id);
        uploadClient.deleteImageById(id);
        searchClient.deleteById(id);
    }

    /**
     * 根据id查询公开笔记
     * @param id
     * @return
     */
    public Note findNotePublic(String id) {
        Note note = (Note) redisTemplate.opsForValue().get("note_" + id);
        if (note == null) {
            note = noteDao.findByIdAndIspublic(id, "1");;
            redisTemplate.opsForValue().set("note_" + id, note);
        }
        noteDao.increaseVisits(id);
        return note;
    }

    /**
     * 根据标签查询公开笔记
     * @param labelid
     * @return
     */
    public List<Note> findPublicNotes(String labelid) {
        return this.noteDao.findPublicNotes("1", labelid);
    }

    public List<Note> findByLabelid(String labelid) {
        return this.noteDao.findByLabelid(labelid);
    }

    public String getNewId() {
        return idWorker.nextId() + "";
    }
}

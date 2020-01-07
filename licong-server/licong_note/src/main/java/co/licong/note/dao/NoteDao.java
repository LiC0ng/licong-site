package co.licong.note.dao;

import co.licong.note.pojo.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 */
public interface NoteDao extends JpaRepository<Note, String>, JpaSpecificationExecutor<Note> {

    List<Note> findByUseridAndLabelid(String userId, String labelId);

    @Query(value = "SELECT new Note(n.id,n.title,n.updatetime) FROM Note as n WHERE n.ispublic=?1")
    Page<Note> findByIspublic(String ispublic, Pageable pageable);

    @Query(value = "SELECT new Note(n.id,n.title) FROM Note as n WHERE n.ispublic=?1 AND n.labelid=?2")
    List<Note> findPublicNotes(String ispublic, String labelid);

    List<Note> findByLabelid(String labelid);

    Note findByIdAndIspublic(String id, String ispublic);

    void deleteByIdAndUserid(String id, String userid);

    @Modifying
    @Query(value="UPDATE tb_note SET comment=comment+1 WHERE id=?",nativeQuery=true)
    void increaseComment(String id);

    @Modifying
    @Query(value="UPDATE tb_note SET comment=comment-1 WHERE id=?",nativeQuery=true)
    void decreaseComment(String id);

    @Modifying
    @Query(value="UPDATE tb_note SET visits=visits+1 WHERE id=?",nativeQuery=true)
    void increaseVisits(String id);
}

package co.licong.search.service;

import co.licong.search.dao.NoteDao;
import co.licong.search.pojo.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class NoteSearchService {

    @Autowired
    private NoteDao noteDao;

    /**
     * 增加文章
     *
     * @param note
     */
    public void add(Note note) {
        noteDao.save(note);
    }

    public Page<Note> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return noteDao.findByTitleOrContentLike(key, key, pageable);
    }
}

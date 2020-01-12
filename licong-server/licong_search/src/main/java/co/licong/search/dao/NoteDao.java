package co.licong.search.dao;

import co.licong.search.pojo.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface NoteDao extends ElasticsearchCrudRepository<Note,String> {

    Page<Note> findByTitleOrContentLike(String title, String content, Pageable pageable);

}

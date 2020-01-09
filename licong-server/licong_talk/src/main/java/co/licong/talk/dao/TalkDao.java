package co.licong.talk.dao;

import co.licong.talk.pojo.Talk;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 数据访问接口
 */
public interface TalkDao extends MongoRepository<Talk, String> {

}

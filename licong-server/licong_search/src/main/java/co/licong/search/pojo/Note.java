package co.licong.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "licong",type = "note")
public class Note implements Serializable {

	private static final long serialVersionUID = 3938554294220243169L;

	@Id
	private String id;

	/* 表示这一列的值索引
	 * 是否索引(index=true，默认就是true所以不写)，就是看该域是否能被搜索
	 * 是否分词，就表示搜索的时候是整体匹配还是单词匹配
	 * 是否存储，就是是否在页面上显示
	 */
	@Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String title;

	@Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

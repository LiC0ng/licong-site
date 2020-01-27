package co.licong.upload.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 笔记图片文件实体类
 */
@Entity
@Table(name = "tb_image")
public class NoteImage {

    private String noteid;  // 笔记Id
    @Id
    private String path;  // 图片地址
    private String userid; //用户id

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

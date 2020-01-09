package co.licong.talk.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;


public class Talk implements Serializable {

    @Id
    private String _id; // 回复id

    private String userid; //回答人id

    private String nickname; // 回答人昵称

    private String content;//回答内容

    private Date createtime;//创建日期

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}

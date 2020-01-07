package co.licong.note.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 笔记实体类
 */
@Entity
@Table(name = "tb_note")
public class Note implements Serializable {

    private static final long serialVersionUID = 4613589788290292299L;

    @Id
    private String id;//ID

    private String userid;//用户ID

    private String labelid; //标签ID

    private String title;//标题

    private String content;//笔记正文

    private Date createtime;//发表日期

    private Date updatetime;//修改日期

    private String ispublic;//是否公开

    private Integer visits;//浏览量

    private Integer comment;//评论数

    public Note() {
    }

    public Note(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Note(String id, String title, Date updatetime) {
        this.id = id;
        this.title = title;
        this.updatetime = updatetime;
    }

    public Note(String id, String userid, String labelid, String title, String content, Date createtime, Date updatetime, String ispublic, Integer visits, Integer comment) {
        this.id = id;
        this.userid = userid;
        this.labelid = labelid;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.ispublic = ispublic;
        this.visits = visits;
        this.comment = comment;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }
}
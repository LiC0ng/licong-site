package co.licong.label.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 标签实体类
 */
@Entity
@Table(name = "tb_label")
public class Label {

    @Id
    private String id;  // Id
    private String labelname;   // 标签名称
    private Long count; // 使用数量
    private String parentid;    // 父标签Id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelName) {
        this.labelname = labelName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentId) {
        this.parentid = parentId;
    }
}

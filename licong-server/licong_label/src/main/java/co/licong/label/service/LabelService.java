package co.licong.label.service;

import co.licong.label.dao.LabelDao;
import co.licong.label.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import util.IdWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 标签业务逻辑类
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据Id查询标签
     *
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 增加Id
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");    // 设置Id
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        //更新数量
        Label label = labelDao.findById(id).get();
        String parentId = label.getParentid();
        while (parentId != null) {
            Label parentLabel = labelDao.findById(parentId).get();
            labelDao.decreaseCount(label.getCount(), parentId);
            parentId = parentLabel.getParentid();
        }
        labelDao.deleteById(id);
    }

    /**
     * 查询子标签
     *
     * @param id 父标签Id
     * @return
     */
    public List<Label> findSubLabels(String id) {
        return labelDao.findByParentid(id);
    }

    /**
     * 标签使用数量+1
     *
     * @param id
     */
    public void increaseCount(String id) {
        Label label = labelDao.findById(id).get();
        labelDao.increaseCount(1l, id);
        String parentId = label.getParentid();
        while (parentId != null) {
            Label parentLabel = labelDao.findById(parentId).get();
            labelDao.increaseCount(1l, parentId);
            parentId = parentLabel.getParentid();
        }
    }

    /**
     * 标签使用数量+1
     *
     * @param id
     */
    public void decreaseCount(String id) {
        Label label = labelDao.findById(id).get();
        labelDao.decreaseCount(1l, id);
        String parentId = label.getParentid();
        while (parentId != null) {
            Label parentLabel = labelDao.findById(parentId).get();
            labelDao.decreaseCount(1l, parentId);
            parentId = parentLabel.getParentid();
        }
    }

    /**
     * 查询关联标签
     * @param id
     * @return
     */
    public List<Label> findLabels(String id) {
        List<Label> labels = new ArrayList<>();
        Label label = labelDao.findById(id).get();
        labels.add(label);
        String parentId = label.getParentid();
        while (parentId != null) {
            Label parentLabel = labelDao.findById(parentId).get();
            labels.add(parentLabel);
            parentId = parentLabel.getParentid();
        }
        Collections.reverse(labels);
        return labels;
    }

    /**
     * 查询根标签
     * @return
     */
    public List<Label> findRootLabels() {
        return labelDao.findRootLables();
    }
}

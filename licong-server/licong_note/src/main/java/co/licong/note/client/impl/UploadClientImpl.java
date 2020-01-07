package co.licong.note.client.impl;

import co.licong.note.client.LabelClient;
import co.licong.note.client.UploadClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class UploadClientImpl implements UploadClient {
    @Override
    public Result deleteImageById(String id) {
        return new Result(false, StatusCode.ERROR, "删除笔记图片时发生错误");
    }
}

package co.licong.note.client.impl;

import co.licong.note.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class LabelClientImpl implements LabelClient {

    @Override
    public Result increaseCount(String id) {
        return new Result(false, StatusCode.ERROR, "有错误发生了");
    }

    @Override
    public Result decreaseCount(String id) {
        return new Result(false, StatusCode.ERROR, "有错误发生了");
    }
}

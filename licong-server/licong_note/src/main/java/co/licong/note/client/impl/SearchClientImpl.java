package co.licong.note.client.impl;

import co.licong.note.client.SearchClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class SearchClientImpl implements SearchClient {
    @Override
    public Result deleteById(String id) {
        return new Result(false, StatusCode.ERROR, "删除搜索数据时发生错误");
    }
}

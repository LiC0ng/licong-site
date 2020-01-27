package co.licong.note.client;

import co.licong.note.client.impl.SearchClientImpl;
import co.licong.note.config.FeignConfig;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "licong-search", fallback = SearchClientImpl.class, configuration = FeignConfig.class)
public interface SearchClient {

    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "id") String id);
}
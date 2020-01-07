package co.licong.note.client;

import co.licong.note.client.impl.LabelClientImpl;
import co.licong.note.config.FeignConfig;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "licong-label", fallback = LabelClientImpl.class, configuration = FeignConfig.class)
public interface LabelClient {

    @RequestMapping(value = "/label/incount/{id}", method = RequestMethod.PUT)
    public Result increaseCount(@PathVariable String id);

    @RequestMapping(value = "/label/decount/{id}", method = RequestMethod.PUT)
    public Result decreaseCount(@PathVariable String id);
}

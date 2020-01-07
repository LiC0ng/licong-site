package co.licong.note.client;

import co.licong.note.client.impl.UploadClientImpl;
import co.licong.note.config.FeignConfig;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "licong-upload", fallback = UploadClientImpl.class, configuration = FeignConfig.class)
public interface UploadClient {

    @RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
    public Result deleteImageById(@PathVariable String id);
}
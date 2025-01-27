package com.qihui.sun.service.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "producer",fallback = FeignFallBack.class)
public interface TestOpenFeignService {

    @GetMapping("/test/{id}")
    String test(@PathVariable("id") String id);
}

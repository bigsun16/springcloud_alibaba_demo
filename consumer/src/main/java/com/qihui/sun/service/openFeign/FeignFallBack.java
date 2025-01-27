package com.qihui.sun.service.openFeign;

import org.springframework.stereotype.Component;

/**
 * 需要搭配sentinel使用，兜底方法
 */
@Component
public class FeignFallBack implements TestOpenFeignService {
    @Override
    public String test(String id) {
        return "default";
    }
}

package com.qihui.sun.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/redis")
public class RedisController {

    //需要配置String和json序列化器,存入对象的时候,会额外存入类型信息，以方便反序列化。
    private final RedisTemplate<String, Object> redisTemplate;
    //默认的使用String序列化器
    private final StringRedisTemplate stringRedisTemplate;

    public RedisController(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/set/{key}/{value}")
    public void set(@PathVariable String key, @PathVariable String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/get/{key}")
    public Object get(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

}

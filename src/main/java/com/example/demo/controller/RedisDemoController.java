package com.example.demo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by on 2020-03-11 10:23
 * @author xutiancheng
 */

@RestController
@RequestMapping("/redis")
public class RedisDemoController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/demo1/{key}", method = RequestMethod.GET)
    public String demo1(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
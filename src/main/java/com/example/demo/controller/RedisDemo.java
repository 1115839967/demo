package com.example.demo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: demo
 * @description:
 * @author: xutiancheng
 * @create: 2020-03-11 10:23
 */

@RestController
@RequestMapping("/redis")
public class RedisDemo {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/demo1/{key}", method = RequestMethod.GET)
    public String demo1(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
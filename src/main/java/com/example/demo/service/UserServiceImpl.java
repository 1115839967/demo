package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xutiancheng
 * @since 2020-03-23 16:56
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public User findOne(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        String key = "USER_LIST";
        List<User> userList = redisTemplate.opsForList().range(key, 0, -1);
        log.info("userList：{}", userList);
        if (CollectionUtils.isEmpty(userList)) {
            userList = userMapper.selectAll();
            redisTemplate.opsForList().leftPushAll(key, userList);
        }

        return userList;
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }
}
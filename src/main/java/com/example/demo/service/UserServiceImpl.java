package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xutiancheng
 * @since 2020-03-23 16:56
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User findOne(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.selectAll();
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>(0);
        }

        return userList;
    }
}
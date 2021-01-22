package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

/**
 *
 * @author xutiancheng
 * @since 2020-03-12 11:30
 */

public interface UserService {

    /**
     * 根据id查询User
     * @param id 主键id
     * @return User实体
     */
    User findOne(String id);

    /**
     * 查询所有用户
     * @return User集合
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 更新用户
     */
    void update(User user);
}
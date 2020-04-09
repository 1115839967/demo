package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by on 2020-03-12 09:30
 *
 * @author xutiancheng
 * @since user实体
 */
@Data
@Table(name = "t_user")
public class User {

    @Id
    private String id;

    private String userName;

    private String password;

    private String realName;
}
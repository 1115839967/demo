package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;

/**
 * @author xutiancheng
 * @since 2021-01-14 15:36
 */
@Data
public class Person implements Serializable {
    private EnumSet<PersonType> personTypes;

    private List<User> userList;
}
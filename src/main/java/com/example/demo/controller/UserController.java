package com.example.demo.controller;

import com.example.demo.domain.Person;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Creat at 2020-03-11 16:32
 *
 * @author xutiancheng
 * @since ..
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/save")
    public JsonResult saveUser(@RequestBody User user) {
        if (StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getUserName())
                || StringUtils.isBlank(user.getPassword())) {
            return new JsonResult(true, "params error");
        }

        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(true, "添加失败");
        }

        return new JsonResult(true, "添加成功");
    }

    @GetMapping("/findOne")
    public JsonResult findOne(@RequestParam(value = "id") String id) {
        User user = userService.findOne(id);
        System.out.println(user);
        return new JsonResult(true, "", user);
    }

    @GetMapping("/findAll")
    public JsonResult findAll() {
        List<User> userList = userService.findAll();
        if (CollectionUtils.isEmpty(userList)) {
            return new JsonResult(true, "查询结果为空");
        }
        return new JsonResult(true, "", userList);
    }

    @PostMapping("/saveList")
    public JsonResult saveList(@RequestBody Person person) {
        for (User user : person.getUserList()) {
            System.out.println(user);
        }

        return new JsonResult(true, "");
    }

    @PostMapping("/update")
    public JsonResult update(@RequestBody User user) {
        String id = user.getId();
        if (StringUtils.isBlank(id)) {
            return new JsonResult(false, "id不能为空！");
        }
        userService.update(user);
        return new JsonResult(true, "操作成功");
    }
}
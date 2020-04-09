package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonResult;
import org.apache.commons.collections4.CollectionUtils;
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

    @PostMapping("/'save")
    public String saveUser(@RequestBody User user) {

        return "success";
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
}
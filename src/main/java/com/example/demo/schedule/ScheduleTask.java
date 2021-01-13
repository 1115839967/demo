package com.example.demo.schedule;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xutiancheng
 * @since 2021-01-11 16:29
 */
@Component
@EnableScheduling // 1.开启定时任务
@EnableAsync // 2.开启多线程
public class ScheduleTask {
    @Resource
    private UserService userService;

    @Async
    @Scheduled(fixedDelay = 2000)
    public void getAll() {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
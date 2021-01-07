package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xutiancheng
 * @since 2020-04-23 09:05
 */
@Slf4j
public class TestDemo {
    public static void main(String[] args) {
        int a = 0;
        if (false) {
            a += 1;
        }

        System.out.println(a);
    }


    @Test
    public void demoA() {
        String[] content = {"阿达的实打实的撒的撒发发发撒飞洒发萨达我打打我达瓦的伟大"};
        JSONObject jsonObject = JSONObject.parseObject(new String(content.toString()));
        System.out.println(jsonObject);
    }

    @Test
    public void demoB() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        String e = "f";

        System.out.println(list.contains(e));

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setUserName("zhangsan");

        User user2 = new User();
        user2.setId("2");
        user2.setUserName("lisi");

        userList.add(user1);
        userList.add(user2);

        Set<String> collect = userList.stream().flatMap(a -> Stream.of(a.getId(), a.getUserName())).distinct().collect(Collectors.toSet());
        System.out.println(collect);
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("张三").add("李四");
        log.info("输出：{}", stringJoiner);
    }

    @Test
    public void ss() {
        String str = "adasdasdsadasdsadasd2222ffff";
        System.out.println(StringUtils.substring(str, -4));
    }

    @Test
    public void date() {
        String date = "Jun 20, 2014 8:56:14 PM";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM d, yyyy HH:mm:ss",
                Locale.ENGLISH);
        try {
            String dateCH = sdf1.format(sdf2.parse(date));
            System.out.println("res:" + dateCH);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void date1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(simpleDateFormat.format(new Date()));
        log.info("123：{}", 123);
    }

    @Test
    public void split() {
        String ip = "192.168.1.120";
        System.out.println(ip.split(",")[0]);
        ip += ", 127.0.0.1";
        System.out.println(ip.split(",")[0]);
        System.out.println(ip.split(",")[1]);

    }
}
package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xutiancheng
 * @since 2020-04-23 09:05
 */
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
    }
}
package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.utils.encryption.RSASignature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xutiancheng
 * @since 2020-04-23 09:05
 */
@Slf4j
public class TestDemo {
    private final String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAmI+vEXVB0vHjqbFcm2nH+DWru7C/4v/44GP5oSX7RHD1jST7ha4SYKQgCdGhPim4TtlcC8GouSv06IglIBiAJQIDAQABAkBv/4uWVW6tXca0nPBPZ6jWHxCkCW3VR/V9RefM1gVQiDmNUMlUJHAoSHJMtzceIC6cgcxnrA8SyhqPmfkwLI1BAiEA1uGE+T6mMV9aIfLkvuoW2xtKBR83Q0jC6xIZKXuoKGcCIQC1wUeEw0ERCdeD5NDE7bP0zSQFvYBQgIl9JLu6SkArkwIgU65LjIz7R6rsfOAMeNTMxdMgxlHbwZYqYkUQC3meiO0CIFXSuFSmZjkHbq6nAzWaEJmNrG7Rdp+Msl9XUxW6LeblAiBAld/nslt0fH6ufsEYFyRNl/4Rw8O+5YgzC1ix2/C1Fw==";
    private final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJiPrxF1QdLx46mxXJtpx/g1q7uwv+L/+OBj+aEl+0Rw9Y0k+4WuEmCkIAnRoT4puE7ZXAvBqLkr9OiIJSAYgCUCAwEAAQ==";
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

    @Test
    public void readTxt() {
        String date = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("date：" + date);
        String path = "/Users/xutiancheng/Desktop/mercinfo/merc_info_" + date + ".txt";
        try {
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader br = new BufferedReader(inputStreamReader);
                String lineTxt = null;
                int i = 1;
                while ((lineTxt = br.readLine()) != null) {
                    String[] lineTxts = lineTxt.split("\\|");
                    for (String str : lineTxts) {
                        System.out.println(str);
                    }

                    String code = lineTxts[0];
                    String crdmercId = lineTxts[1];
                    String name = lineTxts[2];
                    String shortName = lineTxts[3];
                    String brandId = lineTxts[4];
                    String status = lineTxts[5];
                    String type = lineTxts[6];
                    String cardPayAuth = lineTxts[7];
                    System.out.println("----------------------");
                }

                br.close();
                inputStreamReader.close();
            } else {
                log.info("文件不存在！");
            }
        } catch (Exception e) {
            log.info("文件读取错误！");
        }
    }

    @Test
    public void rsa() throws Exception {
        Map<String, String> requestMap = new HashMap<String, String>() {{
            put("codes", "99449,57077");
            put("status", "DISABLED");
        }};

        String paramStr = RSASignature.convertMap2String(requestMap);
        String signStr = RSASignature.sign(paramStr, privateKey);
        System.out.println("param" + paramStr);
        System.out.println("sign：" + signStr);
        String encrypt = RSASignature.encrypt(paramStr, privateKey);
        System.out.println("encrypt：" + encrypt);
        System.out.println("-------decrypt-----------");
        String decrypt = RSASignature.decrypt(encrypt, publicKey);
        System.out.println("decrypt：" + decrypt);
        System.out.println(RSASignature.doCheck(paramStr, signStr, publicKey));
    }
}
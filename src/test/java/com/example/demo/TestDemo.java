package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Car;
import com.example.demo.domain.User;
import com.example.demo.utils.Remote;
import com.example.demo.utils.encryption.RSASignature;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private static final int CONNECT_TIMEOUT = 6000;

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

    public static Session getSession(Remote remote) throws JSchException {
        JSch jSch = new JSch();
        if (Files.exists(Paths.get(remote.getIdentity()))) {
            jSch.addIdentity(remote.getIdentity(), remote.getPassphrase());
        }
        Session session = jSch.getSession(remote.getUser(), remote.getHost(), remote.getPort());
        session.setPassword(remote.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }

    @Test
    public void readTxt2() throws JSchException, SftpException, IOException {
        Remote remote = new Remote();
        remote.setHost("192.168.0.36");
        remote.setPort(22);
        remote.setUser("ucsp");
        remote.setPassword("ucsp.123");
        Session session = getSession(remote);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("PreferredAuthentications", "password");
        session.setConfig(config);
        session.connect();
        if (session.isConnected()) {
            System.out.println("connected");
        }

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect(6000);
        String file = "/web/guest/mercinfo/merc_info_20210112.txt";
        InputStream inputStream = channelSftp.get(file);
        InputStreamReader isr = new InputStreamReader(inputStream, "GBK");
        BufferedReader br = new BufferedReader(isr);
        try {
            LineNumberReader reader = new LineNumberReader(br);
            String txt = null;
            while ((txt = reader.readLine()) != null) {
                System.out.println(txt);
            }
            log.info("数据筛选完毕!");
            reader.close();
            br.close();

        } catch (FileNotFoundException e) {
            log.info(e.getMessage());
        }
    }

    public static List<String> remoteExecute(Session session, String command) throws JSchException {
        log.debug(">> {}", command);
        List<String> resultLines = new ArrayList<>();
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            InputStream input = channel.getInputStream();
            channel.connect(CONNECT_TIMEOUT);
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                String inputLine = null;
                while ((inputLine = inputReader.readLine()) != null) {
                    log.debug("   {}", inputLine);
                    resultLines.add(inputLine);
                }
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception e) {
                        log.error("JSch inputStream close error:", e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("IOcxecption:", e);
        } finally {
            if (channel != null) {
                try {
                    channel.disconnect();
                } catch (Exception e) {
                    log.error("JSch channel disconnect error:", e);
                }
            }
        }

        return resultLines;
    }

    // outputStream转inputStream
    public ByteArrayInputStream parse(final OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    @Test
    public void assertTest() {
        String id = "";
        Assert.hasText(id, "id can not be empty");
    }

    @Test
    public void splitTest() {
        String str = "001||003|004||||||";
        str += "| ";
        System.out.println(str);
        String[] split = str.split("\\|");
        System.out.println(split[0]);
        System.out.println(split[1]);
        System.out.println(split[2]);
        System.out.println(split[3]);
        String a = split[4];
        String b = split[8];
        System.out.println(a);
        System.out.println("：" + b);
    }

    @Test
    public void paseTest() {
        Car car1 = new Car();
        car1.setName("1");
        car1.setNumber(1L);
        Car car2 = new Car();
        car2.setName("2");
        car2.setNumber(2L);
        Car car3 = new Car();
        car3.setName("3");
        car3.setNumber(0L);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        long sum = carList.stream().mapToLong(Car::getNumber).sum();
        System.out.println(sum);
    }
}
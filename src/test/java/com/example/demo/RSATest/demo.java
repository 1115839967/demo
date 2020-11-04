package com.example.demo.RSATest;

import com.example.demo.utils.encryption.RSAEncrypt;
import com.example.demo.utils.encryption.RSASignature;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xutiancheng
 * @since 2020-10-28 18:10
 */
public class demo {

    private final String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAmI+vEXVB0vHjqbFcm2nH+DWru7C/4v/44GP5oSX7RHD1jST7ha4SYKQgCdGhPim4TtlcC8GouSv06IglIBiAJQIDAQABAkBv/4uWVW6tXca0nPBPZ6jWHxCkCW3VR/V9RefM1gVQiDmNUMlUJHAoSHJMtzceIC6cgcxnrA8SyhqPmfkwLI1BAiEA1uGE+T6mMV9aIfLkvuoW2xtKBR83Q0jC6xIZKXuoKGcCIQC1wUeEw0ERCdeD5NDE7bP0zSQFvYBQgIl9JLu6SkArkwIgU65LjIz7R6rsfOAMeNTMxdMgxlHbwZYqYkUQC3meiO0CIFXSuFSmZjkHbq6nAzWaEJmNrG7Rdp+Msl9XUxW6LeblAiBAld/nslt0fH6ufsEYFyRNl/4Rw8O+5YgzC1ix2/C1Fw==";
    private final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJiPrxF1QdLx46mxXJtpx/g1q7uwv+L/+OBj+aEl+0Rw9Y0k+4WuEmCkIAnRoT4puE7ZXAvBqLkr9OiIJSAYgCUCAwEAAQ==";

    @Test
    public void encode() throws Exception {
        Map<String, String> requestMap = new HashMap<String, String>() {{
            put("knowledgeCategoryId", "123");
            put("orgId", "123");
            put("enableSyncBulletin", Boolean.TRUE.toString());
            put("enableTop", Boolean.TRUE.toString());
            put("topType", "ALL");
            put("personInCharge", "张三");
            put("title", "测试");
            put("enableLongTerm", Boolean.TRUE.toString());
            put("startTime", "2020-10-28");
            put("endTime", "2020-10-31");
            put("keywordIds", "123,456");
            put("text", "这是内容");
        }};

        //排序
        String paramStr = RSASignature.convertMap2String(requestMap);
        System.out.println("param：" + paramStr);
        //加签
        String sign = RSASignature.sign(paramStr, privateKey);
        System.out.println("sign：" + sign);
        //验签
        System.out.println(RSASignature.doCheck(paramStr, sign, publicKey));

    }

    /**
     * 生成公钥私钥文件，模块根目录下
     */
    @Test
    public void genKey() {
        RSAEncrypt.genKeyPair("./");
    }
}
package com.example.demo;

import cn.hutool.core.codec.BCD;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.murong.ecp.netpay.sm.SM2Utils;
import com.murong.ecp.netpay.sm.Util;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyPair;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Sm2Test {

    private String publicKey = "3059301306072A8648CE3D020106082A811CCF5501822D03420004D3B8758F1F2E052C25C1E9DCFEE8E6B0002F479834AA4A32D8C77D8B03DF11A085FE448B318333648EF1A1FF0CD8700E2835512890C30697A2F7ACC4BC54830C";

    private String privateKey = "308193020100301306072A8648CE3D020106082A811CCF5501822D04793077020101042089CC1C9384C4FEF5A1C3A0682285BD03BEAF5E96CCE5B2D308E6C157C080E53CA00A06082A811CCF5501822DA14403420004D3B8758F1F2E052C25C1E9DCFEE8E6B0002F479834AA4A32D8C77D8B03DF11A085FE448B318333648EF1A1FF0CD8700E2835512890C30697A2F7ACC4BC54830C";

    @Test
    public void encrypt() {
        long value = Instant.now().toEpochMilli();
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("userId", "lisi");
            put("mobile", "17729980000");
            put("timestamp", value);
            put("sourceFrom", "003");
            put("combinationCode", "00300000001");
        }};

        System.out.println("timestamp:" + value);
        //参数按key升序排序
        String text = MapUtil.sortJoin(params, "&", "=", true);
        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println("加密结果：" + encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println("解密结果：" + decryptStr);

        // 加签
        byte[] sign = sm2.sign(text.getBytes());
        System.out.println("签名: " + BCD.bcdToStr(sign));
        // 验签
        boolean verify = sm2.verify(text.getBytes(), sign);
        System.out.println(verify);
    }

    private void generateKeyPair() {
        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        System.out.println("privateKey:" + BCD.bcdToStr(privateKey));
        byte[] publicKey = pair.getPublic().getEncoded();
        System.out.println("publicKey:" + BCD.bcdToStr(publicKey));
    }

    private String encode() {
        SM2 sm2 = SmUtil.sm2(null, publicKey);
        long value = Instant.now().toEpochMilli();
        System.out.println("timestamp:" + value);
        byte[] encrypt = sm2.encrypt(("123456," + value).getBytes(), KeyType.PublicKey);
        String s = BCD.bcdToStr(encrypt);
        System.out.println("密文：" + s);
        return s;
    }

    private void decode() {
        String encode = encode();
        SM2 sm2 = SmUtil.sm2(privateKey, null);
        byte[] decrypt = sm2.decrypt(encode, KeyType.PrivateKey);
        System.out.println("明文：" + new String(decrypt));
    }

    @Test
    public void aVoid() {
        String aaa = "6D75E04F41BBCEC52D4A97C066AF39779E012F569D6DF71C6E8ABA5020AA594719F441E3493F7D63A8B6971A26F914A134ECDE8BD22BA02A4A3C8F77A767F11C";
        long value = Instant.now().toEpochMilli();
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("userId", "2");
            put("mobile", "18538114632");
            put("timestamp", "1586944994721");
            put("sourceFrom", "999");
            put("combinationCode", "999RxCdjsSx");
        }};

        System.out.println("timestamp:" + value);
        //参数按key升序排序
        String text = MapUtil.sortJoin(params, "&", "=", true);
        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println("加密结果：" + encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println("解密结果：" + decryptStr);

        // 加签
        byte[] sign = sm2.sign(text.getBytes());
        System.out.println(sign);
        System.out.println("签名: " + BCD.bcdToStr(sign));
        System.out.println(BCD.strToBcd(BCD.bcdToStr(sign)));
        // 验签
        System.out.println(BCD.strToBcd(aaa));
        boolean verify = sm2.verify(text.getBytes(), BCD.strToBcd(aaa));
        System.out.println(sm2.verify(text.getBytes(), BCD.strToBcd(aaa)));
        System.out.println(verify);
    }

    @Test
    public void aa() throws IOException {
        String aaa = "6D75E04F41BBCEC52D4A97C066AF39779E012F569D6DF71C6E8ABA5020AA594719F441E3493F7D63A8B6971A26F914A134ECDE8BD22BA02A4A3C8F77A767F11C";
        long value = Instant.now().toEpochMilli();
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("userId", "2");
            put("mobile", "18538114632");
            put("timestamp", "1586944994721");
            put("sourceFrom", "999");
            put("combinationCode", "999RxCdjsSx");
        }};

        System.out.println("timestamp:" + value);
        //参数按key升序排序
        String text = MapUtil.sortJoin(params, "&", "=", true);
        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println("加密结果：" + encryptStr);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println("解密结果：" + decryptStr);

        // 加签
        byte[] sign = sm2.sign(text.getBytes());
        System.out.println(sign);
        System.out.println("签名: " + BCD.bcdToStr(sign));
        System.out.println(BCD.strToBcd(BCD.bcdToStr(sign)));
        // 验签
        System.out.println(BCD.strToBcd(aaa));
        boolean verify = sm2.verify(text.getBytes(), BCD.strToBcd(aaa));
        System.out.println(sm2.verify(text.getBytes(), BCD.strToBcd(aaa)));
        System.out.println(verify);

        byte[] c = SM2Utils.sign("lisi".getBytes(), Util.hexToByte(privateKey), text.getBytes());
        String d = Util.getHexString(c);


    }

    public static void main(String[] args) {
        Sm2Test sm2Test = new Sm2Test();
        sm2Test.decode();
    }
}

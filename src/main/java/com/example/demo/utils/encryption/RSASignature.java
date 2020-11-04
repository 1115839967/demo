package com.example.demo.utils.encryption;

import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * RSASignature
 * Created by murong team
 */
public class RSASignature {
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public RSASignature() {
    }

    public static String sign(String content, String privateKey, String encode) {
        try {
            PKCS8EncodedKeySpec e = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(e);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes(encode));
            byte[] signed = signature.sign();
            return URLEncoder.encode(Base64.encode(signed));
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec e = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(e);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes());
            byte[] signed = signature.sign();
            return URLEncoder.encode(Base64.encode(signed));
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static boolean doCheck(String content, String sign, String publicKey, String encode) {
        try {
            KeyFactory e = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = e.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));
            if (sign.contains("%")) {
                sign = URLDecoder.decode(sign);
            }

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;
        } catch (Exception var9) {
            var9.printStackTrace();
            return false;
        }
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory e = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = e.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            if (sign.contains("%")) {
                sign = URLDecoder.decode(sign);
            }

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;
        } catch (Exception var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public static String encrypt(String content, String privateKey) throws Exception {
        byte[] conByte = content.getBytes("GBK");
        int conLen = conByte.length / 53 + 1;
        String signStr = "";

        for (int i = 0; i < conLen; ++i) {
            byte[] c = Arrays.copyOfRange(conByte, i * 53, 53 * (i + 1));
            byte[] mi = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(privateKey), c);
            String cipher = Base64.encode(mi);
            signStr = signStr + cipher;
        }

        return URLEncoder.encode(signStr);
    }

    public static String decrypt(String content, String publicKey) throws Exception {
        if (content.contains("%")) {
            content = URLDecoder.decode(content);
        }

        int conLen = content.length() / 88;
        byte[] d = new byte[1000];

        for (int restr = 0; restr < conLen; ++restr) {
            String c = content.substring(restr * 88, (restr + 1) * 88);
            byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(publicKey), Base64.decode(c));
            System.arraycopy(res, 0, d, restr * 53, res.length);
        }

        String var7 = new String(d);
        return var7;
    }

    public static String convertMap2String(Map<String, String> data) {
        TreeMap tree = new TreeMap();
        Iterator it = data.entrySet().iterator();

        while (it.hasNext()) {
            Entry sf = (Entry) it.next();
            if (!StringUtils.isEmpty((String) sf.getValue()) && sf.getValue() != " ") {
                tree.put(sf.getKey(), sf.getValue());
            }
        }

        it = tree.entrySet().iterator();
        StringBuffer sf1 = new StringBuffer();

        while (it.hasNext()) {
            Entry en = (Entry) it.next();
            sf1.append((String) en.getKey() + "=" + (String) en.getValue() + "&");
        }

        return sf1.substring(0, sf1.length() - 1);
    }
}

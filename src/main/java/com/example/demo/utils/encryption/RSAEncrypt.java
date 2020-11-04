package com.example.demo.utils.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSAEncrypt
 * Created by murong team
 */
public class RSAEncrypt {
    private static final char[] HEX_CHAR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public RSAEncrypt() {
    }

    public static void genKeyPair(String filePath) {
        KeyPairGenerator keyPairGen = null;

        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException var12) {
            var12.printStackTrace();
        }

        keyPairGen.initialize(512, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        try {
            String e = Base64.encode(publicKey.getEncoded());
            String privateKeyString = Base64.encode(privateKey.getEncoded());
            System.out.println("publicKeyString: " + e);
            System.out.println("privateKeyString: " + privateKeyString);
            FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");
            FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");
            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(e);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    public static String loadPublicKeyByFile(String path) throws Exception {
        try {
            BufferedReader e = new BufferedReader(new FileReader(path + "/publicKey.keystore"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();

            while ((readLine = e.readLine()) != null) {
                sb.append(readLine);
            }

            e.close();
            return sb.toString();
        } catch (IOException var4) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException var5) {
            throw new Exception("公钥输入流为空");
        }
    }

    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws Exception {
        try {
            byte[] e = Base64.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(e);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException var4) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException var5) {
            throw new Exception("公钥非法");
        } catch (NullPointerException var6) {
            throw new Exception("公钥数据为空");
        }
    }

    public static String loadPrivateKeyByFile(String path) throws Exception {
        try {
            BufferedReader e = new BufferedReader(new FileReader(path + "/privateKey.keystore"));
            String readLine = null;
            StringBuilder sb = new StringBuilder();

            while ((readLine = e.readLine()) != null) {
                sb.append(readLine);
            }

            e.close();
            return sb.toString();
        } catch (IOException var4) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException var5) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
        try {
            byte[] e = Base64.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(e);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException var4) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException var5) {
            throw new Exception("私钥非法");
        } catch (NullPointerException var6) {
            throw new Exception("私钥数据为空");
        }
    }

    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        } else {
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("RSA");
                cipher.init(1, publicKey);
                byte[] e = cipher.doFinal(plainTextData);
                return e;
            } catch (NoSuchAlgorithmException var4) {
                throw new Exception("无此加密算法");
            } catch (NoSuchPaddingException var5) {
                var5.printStackTrace();
                return null;
            } catch (InvalidKeyException var6) {
                throw new Exception("加密公钥非法,请检查");
            } catch (IllegalBlockSizeException var7) {
                throw new Exception("明文长度非法");
            } catch (BadPaddingException var8) {
                throw new Exception("明文数据已损坏");
            }
        }
    }

    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        } else {
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("RSA");
                cipher.init(1, privateKey);
                byte[] e = cipher.doFinal(plainTextData);
                return e;
            } catch (NoSuchAlgorithmException var4) {
                throw new Exception("无此加密算法");
            } catch (NoSuchPaddingException var5) {
                var5.printStackTrace();
                return null;
            } catch (InvalidKeyException var6) {
                throw new Exception("加密私钥非法,请检查");
            } catch (IllegalBlockSizeException var7) {
                throw new Exception("明文长度非法");
            } catch (BadPaddingException var8) {
                throw new Exception("明文数据已损坏");
            }
        }
    }

    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        } else {
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("RSA");
                cipher.init(2, privateKey);
                byte[] e = cipher.doFinal(cipherData);
                return e;
            } catch (NoSuchAlgorithmException var4) {
                throw new Exception("无此解密算法");
            } catch (NoSuchPaddingException var5) {
                var5.printStackTrace();
                return null;
            } catch (InvalidKeyException var6) {
                throw new Exception("解密私钥非法,请检查");
            } catch (IllegalBlockSizeException var7) {
                throw new Exception("密文长度非法");
            } catch (BadPaddingException var8) {
                throw new Exception("密文数据已损坏");
            }
        }
    }

    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        } else {
            Cipher cipher = null;

            try {
                cipher = Cipher.getInstance("RSA");
                cipher.init(2, publicKey);
                byte[] e = cipher.doFinal(cipherData);
                return e;
            } catch (NoSuchAlgorithmException var4) {
                throw new Exception("无此解密算法");
            } catch (NoSuchPaddingException var5) {
                var5.printStackTrace();
                return null;
            } catch (InvalidKeyException var6) {
                throw new Exception("解密公钥非法,请检查");
            } catch (IllegalBlockSizeException var7) {
                throw new Exception("密文长度非法");
            } catch (BadPaddingException var8) {
                throw new Exception("密文数据已损坏");
            }
        }
    }

    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.length; ++i) {
            stringBuilder.append(HEX_CHAR[(data[i] & 240) >>> 4]);
            stringBuilder.append(HEX_CHAR[data[i] & 15]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }

        return stringBuilder.toString();
    }
}

package com.blog.me.security;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    public static final String ALGORITHM = "RSA";
    private static final int SIZE = 1024;

    public void initKey(){
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //初始化密钥生成器
        keyPairGenerator.initialize(SIZE);

        //生成私钥
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        this.privateKey= keyPair.getPrivate();
        //生成公钥
        this.publicKey= keyPair.getPublic();
    }

    //公钥-加密
    public String publicKeyEncrypt(String data){
        try {
            Cipher cipher =  Cipher.getInstance(this.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,this.publicKey);
            byte[] dataBytes= data.getBytes("utf-8");
            byte[] encrypt = cipher.doFinal(dataBytes);
            return new BASE64Encoder().encode(encrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //私钥-解密
    public String privateKeyDecrypt(String data){
        try {
            Cipher cipher = Cipher.getInstance(this.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,this.privateKey);
            byte[] decrypt = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
            return new String(decrypt,"utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //私钥-加密
    public String privateKeyEncrypt(String data){
        try {
            Cipher cipher =  Cipher.getInstance(this.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,this.privateKey);
            byte[] dataBytes= data.getBytes("utf-8");
            byte[] encrypt = cipher.doFinal(dataBytes);
            return new BASE64Encoder().encode(encrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //公钥-解密
    public String publicKeyDecrypt(String data){
        try {
            Cipher cipher = Cipher.getInstance(this.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,this.publicKey);
            byte[] decrypt = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
            return new String(decrypt,"utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    //私钥
    public String getPrivateKey(){
        return new BASE64Encoder().encode(this.privateKey.getEncoded());
    }

    //公钥
    public String getPublickey(){
        return new BASE64Encoder().encode(this.publicKey.getEncoded());
    }


    //私钥
    public static PrivateKey getPrivateKey(String privateKey){
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(privateKey));
            return keyFactory.generatePrivate(x509EncodedKeySpec);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    //公钥
    public static PublicKey getPublicKey(String publicKey){
        //通过X509编码的Key指令获得公钥对象
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(publicKey));
            return keyFactory.generatePublic(x509EncodedKeySpec);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

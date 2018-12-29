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
    /** 加密算法:RSA */
    private static final String ALGORITHM = "RSA";
    /** 签名算法：MD5withRSA or SHA1withRSA */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
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

    /**
     * 公钥-加密
     * @param data
     * @return
     */
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

    /**
     * 私钥-解密
     * @param data
     * @return
     */
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

    /**
     * 私钥-加密
     * @param data
     * @return
     */
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

    /**
     * 公钥-解密
     * @param data
     * @return
     */
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

    /**
     * 私钥-签名
     * @param data 签名的数据
     * @return
     */
    public String sign(String data){
        try {
            Signature signature = Signature.getInstance(this.SIGNATURE_ALGORITHM);
            signature.initSign(this.privateKey);
            signature.update(data.getBytes("utf-8"));
            byte[] signBytes = signature.sign();
            return new BASE64Encoder().encode(signBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥-验签
     * @param data  验签的数据
     * @param sign  数据的签名
     * @return
     */
    public boolean verify(String data,String sign){
        try {
            byte[] dataBytes = data.getBytes("utf-8");
            byte[] signBytes = new BASE64Decoder().decodeBuffer(sign);
            Signature signature = Signature.getInstance(this.SIGNATURE_ALGORITHM);
            signature.initVerify(this.publicKey);
            signature.update(dataBytes);
            return signature.verify(signBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取私钥
     * @return
     */
    public String getPrivateKey(){
        return new BASE64Encoder().encode(this.privateKey.getEncoded());
    }

    /**
     * 获取公钥
     * @return
     */
    public String getPublickey(){
        return new BASE64Encoder().encode(this.publicKey.getEncoded());
    }

    /**
     * 私钥
     * @param privateKey
     * @return
     */
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

    /**
     * 公钥
     * @param publicKey
     * @return
     */
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

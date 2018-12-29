package security;

import com.alibaba.fastjson.JSONObject;
import com.blog.me.security.RSA;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.security.interfaces.RSAPrivateCrtKey;

public class RSATest {


    @Test
    //测试生成公私钥
    public void testRsaKey(){
        RSA rsa = new RSA();
        rsa.initKey();
        System.out.println("================");

        System.out.println(rsa.getPrivateKey());
        System.out.println("---------------");
        System.out.println(rsa.getPublickey());

        System.out.println("================");

        System.out.println(RSA.getPrivateKey(rsa.getPrivateKey()));
        System.out.println("---------------");
        System.out.println(RSA.getPublicKey(rsa.getPublickey()));

    }

    @Test
    //测试加密和解密
    public void EncAndDecTest(){
        String data = "asdasdasdad!fghbweoirj12";
        System.out.println("加密数据："+data);
        RSA rsa = new RSA();
        rsa.initKey();
        String encrypt =  rsa.publicKeyEncrypt(data);
        System.out.println("公钥加密："+encrypt);
        String decrypt =  rsa.privateKeyDecrypt(encrypt);
        System.out.println("私钥解密："+decrypt);

        System.out.println("====================");

        String encrypt1 =  rsa.privateKeyEncrypt(data);
        System.out.println("私钥加密："+encrypt1);
        String decrypt1 =  rsa.publicKeyDecrypt(encrypt1);
        System.out.println("公钥解密："+decrypt1);

    }


    @Test
    //测试签名和验签
    public void signAndVerifyTest(){
        JSONObject data = new JSONObject();
        data.put("code",200);
        data.put("msg","success");
        System.out.println("数据"+data.toString());

        RSA rsa = new RSA();
        rsa.initKey();
        String sign= rsa.sign(data.toString());
        System.out.println("签名："+sign);
        boolean verify = rsa.verify(data.toString(),sign);
        System.out.println("验签："+verify);
    }
}

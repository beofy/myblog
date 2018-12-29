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


    @Test
    public void EncAndDecTest1(){
        String privateKey ="MIICXQIBAAKBgQDlOJu6TyygqxfWT7eLtGDwajtNFOb9I5XRb6khyfD1Yt3YiCgQ\n" +
                "WMNW649887VGJiGr/L5i2osbl8C9+WJTeucF+S76xFxdU6jE0NQ+Z+zEdhUTooNR\n" +
                "aY5nZiu5PgDB0ED/ZKBUSLKL7eibMxZtMlUDHjm4gwQco1KRMDSmXSMkDwIDAQAB\n" +
                "AoGAfY9LpnuWK5Bs50UVep5c93SJdUi82u7yMx4iHFMc/Z2hfenfYEzu+57fI4fv\n" +
                "xTQ//5DbzRR/XKb8ulNv6+CHyPF31xk7YOBfkGI8qjLoq06V+FyBfDSwL8KbLyeH\n" +
                "m7KUZnLNQbk8yGLzB3iYKkRHlmUanQGaNMIJziWOkN+N9dECQQD0ONYRNZeuM8zd\n" +
                "8XJTSdcIX4a3gy3GGCJxOzv16XHxD03GW6UNLmfPwenKu+cdrQeaqEixrCejXdAF\n" +
                "z/7+BSMpAkEA8EaSOeP5Xr3ZrbiKzi6TGMwHMvC7HdJxaBJbVRfApFrE0/mPwmP5\n" +
                "rN7QwjrMY+0+AbXcm8mRQyQ1+IGEembsdwJBAN6az8Rv7QnD/YBvi52POIlRSSIM\n" +
                "V7SwWvSK4WSMnGb1ZBbhgdg57DXaspcwHsFV7hByQ5BvMtIduHcT14ECfcECQATe\n" +
                "aTgjFnqE/lQ22Rk0eGaYO80cc643BXVGafNfd9fcvwBMnk0iGX0XRsOozVt5Azil\n" +
                "psLBYuApa66NcVHJpCECQQDTjI2AQhFc1yRnCU/YgDnSpJVm1nASoRUnU8Jfm3Oz\n" +
                "uku7JUXcVpt08DFSceCEX9unCuMcT72rAQlLpdZir876";

        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlOJu6TyygqxfWT7eLtGDwajtN\n" +
                "FOb9I5XRb6khyfD1Yt3YiCgQWMNW649887VGJiGr/L5i2osbl8C9+WJTeucF+S76\n" +
                "xFxdU6jE0NQ+Z+zEdhUTooNRaY5nZiu5PgDB0ED/ZKBUSLKL7eibMxZtMlUDHjm4\n" +
                "gwQco1KRMDSmXSMkDwIDAQAB";
        RSA rsa= new RSA();
        rsa.setPrivateKey(RSA.getPrivateKey(privateKey)); ;
        rsa.setPublicKey(RSA.getPublicKey(publicKey));


        String data = "asdasdasdad!fghbweoirj12";

        System.out.println("加密数据："+data);
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
}

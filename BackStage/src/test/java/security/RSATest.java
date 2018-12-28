package security;

import com.blog.me.security.RSA;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.security.interfaces.RSAPrivateCrtKey;

public class RSATest {


    @Test
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
}

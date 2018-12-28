package security;

import com.blog.me.security.Des;
import com.blog.me.security.Encrypt;
import org.junit.Test;

public class DESTest {

   private String desKey="7uAWkt4MbcoZHHsS";

   @Test
   public void enCAndDec(){
       String str = "123456";
       System.out.println("加密的字符："+str);
       String enc= Encrypt.encrypt3DES(str,this.desKey);
       System.out.println("加密后的字符："+enc);
       String dec = Encrypt.decrypt3DES(enc,this.desKey);
       System.out.println("解密后的字符："+dec);
    }
}

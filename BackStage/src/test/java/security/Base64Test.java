package security;


import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Test {

    @Test
    public  void Base64test() {
        String str="123456";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode="";
        try {
            encode= base64Encoder.encode(str.getBytes("utf-8"));
            System.out.println(encode);
            BASE64Decoder base64Decoder = new BASE64Decoder();
            String decoder= new String(base64Decoder.decodeBuffer(encode)) ;
            System.out.println(decoder);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }


}

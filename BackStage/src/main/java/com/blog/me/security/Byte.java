package com.blog.me.security;

public class Byte {

    public static boolean Compare(byte[] input1, byte[] input2) {
        if (input1 != null && input2 != null) {
            if (input1.length != input2.length) {
                return false;
            } else {
                for(int i = 0; i < input1.length; ++i) {
                    if (input1[i] != input2[i]) {
                        return false;
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public static String byte2hex(byte[] buffer) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < buffer.length; ++n) {
            stmp = Integer.toHexString(buffer[n] & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] buffer) {
        if (buffer.length % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        } else {
            byte[] b2 = new byte[buffer.length / 2];

            for(int n = 0; n < buffer.length; n += 2) {
                String item = new String(buffer, n, 2);
                b2[n / 2] = (byte)Integer.parseInt(item, 16);
            }

            return b2;
        }
    }
}

package com.blog.me.security;

import java.nio.charset.Charset;

public class Ses {
    private byte[] SES_IV = new byte[]{-32, 32, 58, 8, 73, 6, 36, 92, -62, 41, -84, 18, -111, -107, -28, 121};
    private byte[] key = new byte[16];

    public Ses(String key) throws Exception {
        int len = key.length();
        if (len < 16) {
            throw new Exception("key length must be greater than or equal to 16 characters.");
        } else {
            System.arraycopy(key.getBytes(Charset.forName("GB2312")), 0, this.key, 0, 16);

            for(int i = 0; i < 16; ++i) {
                this.key[i] ^= this.SES_IV[i];
            }

        }
    }

    public void encrypt(byte[] input, byte[] output) {
        int len = this.complementInput(input, output);
        this.encryptMatrixTransform(output, len);
        this.xor(output, len);
    }

    public int decrypt(byte[] input, int len, byte[] output) throws Exception {
        System.arraycopy(input, 0, output, 0, len);
        this.xor(output, len);
        this.decryptMatrixTransform(output, len);
        return this.getDecryptResultLength(output, len);
    }

    public int getEncryptResultLength(byte[] input) {
        int len = input.length;
        int complement_len = 8 - len % 8;
        if (complement_len == 1) {
            complement_len = 9;
        }

        return complement_len + len;
    }

    private int getDecryptResultLength(byte[] output, int len) throws Exception {
        int complement_len = 0;

        int i;
        for(i = len - 1; output[i--] == 0; ++complement_len) {
            ;
        }

        int num = output[i + 1];
        if (complement_len > 0 && num != complement_len) {
            throw new Exception("Invalid ciphertext format.");
        } else {
            return complement_len == 0 ? len : len - complement_len - 1;
        }
    }

    private int complementInput(byte[] input, byte[] output) {
        int len = input.length;
        int complement_len = this.getEncryptResultLength(input) - len;
        System.arraycopy(input, 0, output, 0, len);
        if (complement_len == 0) {
            return len;
        } else {
            for(int i = len; i < len + complement_len; ++i) {
                output[i] = 0;
            }

            output[len] = (byte)(complement_len - 1);
            return len + complement_len;
        }
    }

    private void encryptMatrixTransform(byte[] output, int len) {
        int row = len / 4;
        byte[] t = new byte[row];

        int i;
        for(i = 0; i < row; ++i) {
            t[i] = output[i * 4];
        }

        int j;
        for(i = 0; i < row; ++i) {
            for(j = 0; j < 3; ++j) {
                output[i * 4 + j] = output[i * 4 + j + 1];
            }

            output[i * 4 + 3] = t[i];
        }

        t = new byte[4];

        for(i = 0; i < 4; ++i) {
            t[i] = output[i];
        }

        for(i = 0; i < row - 1; ++i) {
            for(j = 0; j < 4; ++j) {
                output[i * 4 + j] = output[(i + 1) * 4 + j];
            }
        }

        for(i = 0; i < 4; ++i) {
            output[(row - 1) * 4 + i] = t[i];
        }

    }

    private void decryptMatrixTransform(byte[] output, int len) {
        int row = len / 4;
        byte[] t = new byte[4];

        int i;
        for(i = 0; i < 4; ++i) {
            t[i] = output[(row - 1) * 4 + i];
        }

        int j;
        for(i = row - 1; i > 0; --i) {
            for(j = 0; j < 4; ++j) {
                output[i * 4 + j] = output[(i - 1) * 4 + j];
            }
        }

        for(i = 0; i < 4; ++i) {
            output[i] = t[i];
        }

        t = new byte[row];

        for(i = 0; i < row; ++i) {
            t[i] = output[i * 4 + 3];
        }

        for(i = 0; i < row; ++i) {
            for(j = 3; j > 0; --j) {
                output[i * 4 + j] = output[i * 4 + j - 1];
            }

            output[i * 4] = t[i];
        }

    }

    private void xor(byte[] output, int len) {
        int key_position = 0;

        for(int i = 0; i < len; ++i) {
            output[i] ^= this.key[key_position++];
            if (key_position >= 16) {
                key_position = 0;
            }
        }

    }

}

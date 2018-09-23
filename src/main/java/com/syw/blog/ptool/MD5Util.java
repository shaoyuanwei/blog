package com.syw.blog.ptool;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MD5Util {

    /**
     * 利用MD5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密后的字符串
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;

    }

    /**
     * 密码校验
     * @param password
     * @param md5password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public boolean checkPassword(String password, String md5password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (password.equals(md5password)) {
           return true;
        } else {
            return false;
        }
    }

}

package com.syw.blog.ptool;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 利用MD5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMD5(String str){

        String newStr = "";

        try {

            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            //加密后的字符串
            newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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
    public static boolean checkPassword(String password, String md5password) {

        try {

            if (password.equals(md5password)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

}

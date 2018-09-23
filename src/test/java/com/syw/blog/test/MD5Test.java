package com.syw.blog.test;

import com.syw.blog.ptool.MD5Util;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5Test {

    @Test
    public void md5ToString() {

        MD5Util util = new MD5Util();

        try {
            System.out.println(util.EncoderByMD5("admin"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

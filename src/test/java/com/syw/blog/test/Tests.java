package com.syw.blog.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    private static Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void ListTest() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        logger.info("list信息:{}", list.toString());
        String string = list.toString();
        string = string.substring(1, string.length() - 1);
        logger.info("string截取后的值:{}", string);
        String[] list1 = string.split(",");
        for (String str : list1) {
            logger.info("str信息:{}", str.trim());
        }

    }

    @Test
    public void ListEquals() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        logger.info("list信息:{}", list.toString());
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(1);
        list2.add(2);
        logger.info("list2信息:{}", list2.toString());
        logger.info("list1和list2是否相同:{}", list.equals(list2));
        boolean b = false;
        for (Integer i : list) {
            for (Integer i2 : list2) {
                if (i == i2) {
                    logger.info("i={}和i2={}的比较:{}", i, i2, i == i2);
                    b = i == i2;
                    break;
                } else {
                    b = i == i2;
                    logger.info("else:-------->i={}和i2={}的比较:{}", i, i2, i == i2);
                }
            }
        }
        logger.info("b的值为:{}", b);
    }

}

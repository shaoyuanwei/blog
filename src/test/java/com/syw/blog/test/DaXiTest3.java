package com.syw.blog.test;

import java.util.*;

/**
 * 两个字符
 */
public class DaXiTest3 {

    public static void main(String[] args) {
        int answer = alternate("beabeefeab");
        System.out.println(answer);
    }

    static int alternate(String s) {
        // 记录是交替数据的数据
        List<Character> list = null;
        Map<String, Integer> map = new HashMap<>();

        // 将字符串转为字符串数组
        char[] chars = s.toCharArray();

        //采集有多少种不同的字符
        Set<Character> set = new LinkedHashSet<>();
        for (char c : chars) {
            if (set.contains(c)) {
                continue;
            } else {
                set.add(c);
            }
        }

        //去除字符串到2个的组合查看是否符合交替要求
        for (Character c : set) {
            for (Character ch : set) {
                if (c == ch) {
                    continue;
                } else {
                    // chars 加入到list
                    list = new LinkedList<>();
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] == c || chars[i] == ch) {
                            list.add(chars[i]);
                        }
                    }
                    // 检验是否符合交替要求
                    char flag = 0;
                    // 是否为交替 不是 false 是 true
                    boolean bool = true;
                    for (Character character : list) {
                        if (flag == 0) {
                            flag = character;
                        } else {
                            // 如果一样 则该答案不是一个交替的字符串
                            if (character == flag) {
                                bool = false;
                                break;
                            } else {
                                flag = character;
                            }
                        }
                    }
                    if (bool) {
                        map.put(list.toString(), list.size());
                    }

                }
            }
        }

        int max = 0;
        String answers = "";
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            if (value > max) {
                max = value;
                answers = key;
            }
        }

        System.out.println(answers);
        return max;
    }

}

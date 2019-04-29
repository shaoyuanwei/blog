package com.syw.blog.test;

/**
 * 递归数字和
 */
public class DaXiTest2 {

    static int sum;

    static int superDigit(String n, int k) {
        sum = 0;
        // 递归出口
        if (n.length() == 1 || k == 0){
            System.out.println("n:" + n);
            return Integer.valueOf(n);
        } else {
            // 主要递归
            k--;
            for (int i = 0; i < n.length(); i++) {
                System.out.println(n.charAt(i));
                sum = sum + Integer.parseInt(String.valueOf(n.charAt(i)));
                System.out.println(sum);
            }
            System.out.println("sum:" + sum);
            return superDigit("" + sum, k);
        }

    }

    public static void main(String[] args) {
        int answer = superDigit("9875", 4);
        System.out.println(answer);
    }


}

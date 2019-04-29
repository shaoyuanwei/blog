package com.syw.blog.test;

/**
 * 奇怪的计数器
 */
public class DaXiTest1 extends Thread {

    static long initn = 3;// 递减初始化

    static long n = 3; // 递减总数

    static long time = 0; // 时间记录

    static boolean bool = true;// 是否是新的一个周期

    @Override
    public void run() {
        while(true) {
            try {
                long answer = strangeCounter(3);
                if (answer != 0) {
                    System.out.println(answer);
                    sleep(100000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    long strangeCounter(long t) throws InterruptedException {
        if (n > 1) {
            if (bool) {
                bool = false;
                time++;
            } else {
                n--;
                time++;
            }
        } else if (n == 1 && time < t) {
            initn *= 2;
            n = initn;
            bool = true;
            System.out.println("n:" + n);
        }
        if (time == t) {
            System.out.println(time + "------------" + t);
            return n;
        }
        Thread.sleep(1000);

        return 0;

    }

    public static void main(String[] args) {
        Thread thread = new DaXiTest1();
        thread.start();
    }

}

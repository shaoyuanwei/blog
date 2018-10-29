package com.syw.blog.ptool;

public class RandomUtil {

    public final static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                                        'h', 'i', 'j','k', 'l', 'm', 'n',
                                        'o', 'p', 'q', 'r', 's', 't',
                                        'u', 'v', 'w', 'x', 'y', 'z',
                                        'A', 'B', 'C', 'D', 'E', 'F', 'G',
                                        'H', 'I', 'J', 'K', 'L', 'M', 'N',
                                        'O', 'P', 'Q', 'R', 'S', 'T',
                                        'U', 'V', 'W', 'X', 'Y', 'Z',
                                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String generateRandom(int number) {

        String random = "";

        for (int i = 0; i < number; i++) {
            random += chars[(int) (Math.random() * 62)];
        }

        return random;

    }

}

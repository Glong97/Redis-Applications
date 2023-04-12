package com.glong.redis_emailcode.utils;

import java.util.Random;

public class RandomUtil {
    public static String getSixBitRandom(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}

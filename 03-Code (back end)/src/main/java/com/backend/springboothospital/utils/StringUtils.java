package com.backend.springboothospital.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length) {

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String name = df.format(day) + sb.toString();
        return name;
    }
}

package ru.kpfu.itis.group501.volkov.helpers;

/**
 * Created by volkov on 21.10.2016.
 */

import java.security.SecureRandom;

/**
 * Created by volkov on 21.10.2016.
 */
public class Token {
    private String string = null;

    public static String getToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }



}
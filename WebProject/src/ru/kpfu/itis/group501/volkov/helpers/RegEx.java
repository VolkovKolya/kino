package ru.kpfu.itis.group501.volkov.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by volkov on 23.10.2016.
 */
public class RegEx {
    public boolean IsRight(String s){
        Pattern data = Pattern.compile("");
        Matcher m = data.matcher(s);
        //return m.matches();
        return true;
    }

}

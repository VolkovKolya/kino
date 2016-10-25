package ru.kpfu.itis.group501.volkov.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by volkov on 24.10.2016.
 */
public class DataTime {
    public String getData(){
        String x = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss").format(new Date());
        return x;
    }
}

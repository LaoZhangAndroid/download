package com.laozhang.affectjava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zzc on 2019/4/9.
 */
public class TimeUtil {

    private static final SimpleDateFormat ymdFormat;
    private static final SimpleDateFormat ymdHmsFormat;
    private static final Date now;

    static {
        ymdFormat = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        ymdHmsFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.CHINA);
        now = new Date(System.currentTimeMillis());
    }

    public static String currentDate(){
        return ymdFormat.format(now);
    }

    public static  String currentTime(){
        return ymdHmsFormat.format(now);
    }
}

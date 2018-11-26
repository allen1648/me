package com.stan.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * @return 年月日时分秒毫秒 20180101101010000
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * @param dbTime 20180101101010000
     * @return 2018-01-01
     */
    public static String getDayTime(String dbTime) {
        String year = dbTime.substring(0,4);
        String month = dbTime.substring(4,6);
        String day = dbTime.substring(6,8);
        return year + "-" + month + "-" + day;
    }
}

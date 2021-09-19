package com.bedfordshire.helpmebackend.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Lakitha Prabudh
 */
public class CommonUtil {
    public static String HELP_REQUEST_STATUS_ONGOING = "ONGOING";
    public static String HELP_REQUEST_STATUS_PENDING = "PENDING";
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date getDateByString(String date) throws ParseException {
        LocalDateTime formattedDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return Date.from(formattedDate.atZone(ZoneId.of("Asia/Colombo")).toInstant());
    }

    public static Date getDateByString2(String date) throws ParseException {
        LocalDateTime formattedDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        return Date.from(formattedDate.atZone(ZoneId.of("Asia/Colombo")).toInstant());
    }

    public static String getStringDateByDate(Date date) {
//        format.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));
        return format.format(date);
    }
}

package com.payx.payxwallet.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionIdGenerator {

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Kolkata");

    public static String generate(String serviceType, String node){
        ZonedDateTime now = ZonedDateTime.now(ZONE_ID);

        String date = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String time = now.format(DateTimeFormatter.ofPattern("HHmm"));
        String second = now.format((DateTimeFormatter.ofPattern("ss")));
        int millisInt = now.getNano() / 1000000;
        String millis = String.format("%03d", millisInt);

        return serviceType + date + "." + time + "." + node + second + millis;
    }
}

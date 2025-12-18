package com.payx.payxwallet.common;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class TransactionIdGenerator {

    private static final Logger logger = Logger.getLogger(TransactionIdGenerator.class.getName());
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Kolkata");

    public static String generate(String serviceType, String node){
        logger.info("Generating transaction ID for serviceType: " + serviceType + ", node: " + node);
        
        ZonedDateTime now = ZonedDateTime.now(ZONE_ID);

        String date = now.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String time = now.format(DateTimeFormatter.ofPattern("HHmm"));
        String second = now.format((DateTimeFormatter.ofPattern("ss")));
        int millisInt = now.getNano() / 1000000;
        String millis = String.format("%03d", millisInt);

        String transactionId = serviceType + date + "." + time + "." + node + second + millis;
        logger.info("Generated transaction ID: " + transactionId);

        return transactionId;
    }
}
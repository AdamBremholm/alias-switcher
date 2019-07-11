package org.adam.aliasswitcher;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Auth {

    final String host = "192.168.1.2";

    public static String fauxapiAuth(){
        final String apiKey = "***REMOVED***";
        final String apiSecret = "***REMOVED***";
        String utcStamp = getUTCTimeStamp();
        String nonce = getNonce();
        String sha256hex = DigestUtils.sha256Hex(apiSecret+utcStamp+nonce);
        StringBuilder auth = new StringBuilder();
        auth.append(apiKey).append(":").append(utcStamp).append(":").append(nonce).append(":").append(sha256hex);
        System.out.println(auth.toString());
        return auth.toString();

    }

    private static String getNonce() {
        RandomString gen = new RandomString(8, ThreadLocalRandom.current());
        RandomString session = new RandomString();
        String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
        RandomString tickets = new RandomString(8, new SecureRandom(), easy);
       return tickets.nextString();
    }

    private static String getUTCTimeStamp() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyyMMdd'Z'HHmmss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));

        String timeStamp = dateFormatGmt.format(new Date());
        System.out.println(timeStamp);


        return timeStamp;
    }


}

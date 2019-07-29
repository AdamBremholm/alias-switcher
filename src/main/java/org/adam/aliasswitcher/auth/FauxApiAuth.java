package org.adam.aliasswitcher.auth;
import org.apache.commons.codec.digest.DigestUtils;


import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import java.util.concurrent.ThreadLocalRandom;



public class FauxApiAuth {


    public static String fauxapiAuth(String apiKey, String apiSecret){
        String utcStamp = getUTCTimeStamp();
        String nonce = getNonce();
        String sha256hex = DigestUtils.sha256Hex(apiSecret+utcStamp+nonce);
        return apiKey + ":" + utcStamp + ":" + nonce + ":" + sha256hex;

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

        return dateFormatGmt.format(new Date());
    }


}

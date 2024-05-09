package com.tc.utl;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sleepSeconds(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMilliSeconds(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getOutImgPath() {
        return "/home/tc/github/algQuestion/src/main/resources/out_img/";
    }
}

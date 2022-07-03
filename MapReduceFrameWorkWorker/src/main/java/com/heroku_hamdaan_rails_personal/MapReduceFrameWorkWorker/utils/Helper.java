package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils;

public class Helper {
    public static void SLEEP(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception e) {
        }
    }

    public static int MOD_FILE_WRITE_DECIDE(String key, int reduceCount) {
        return Math.abs(key.hashCode() % reduceCount);
    }
}

package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.examples;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.IReduceTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.util.*;

public class WordCountReduce implements IReduceTask {
    public KeyValuePair reduce(String key, List<String> values) {
        int countSum = 0;
        for (String count: values) {
            countSum += Integer.valueOf(count);
        }
        return new KeyValuePair(key, String.valueOf(countSum));
    }
}

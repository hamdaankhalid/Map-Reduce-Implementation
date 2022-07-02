package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.examples;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.IReduceTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.util.*;

public class WordCountReduce implements IReduceTask {
    public List<KeyValuePair> reduce(String content) {
        return new ArrayList<KeyValuePair>(
                Arrays.asList(
                        new KeyValuePair("test", "1")
                )
        );
    }

}

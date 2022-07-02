package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.examples;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.IMapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCountMap implements IMapTask {
    public List<KeyValuePair> map(String content) {
        return new ArrayList<KeyValuePair>(Arrays.asList(
                new KeyValuePair("test", "1")
        ));
    }
}

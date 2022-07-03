package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.examples;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Tasks.IMapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.KeyValuePair;

import java.util.*;

public class WordCountMap implements IMapTask {
    public List<KeyValuePair> map(String content) {
       // given the content as string of one file count occurences of words and create list of key val pairs
        String[] words = content.split("\\s+");

        if (words.length == 0) {
            return null;
        }
        Set<String> uniqueWords = new HashSet<>(List.of(words));
        List<KeyValuePair> result = new ArrayList<>();
        for (String word : uniqueWords) {
            int count = 0;
            for (String occurence: words) {
                count += occurence.equals(word) ? 1 : 0;
            }
            result.add(new KeyValuePair(word, String.valueOf(count)));
        }
        return result;
    }
}

package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.utils;


import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;

@Component
public class StoreInitializer {

    private String INPUT_DIRECTORY;
    private int REDUCE_NUMBER;

    @PostConstruct
    void setInputDirAndReduce() {
        INPUT_DIRECTORY = "/Users/hamdaankhalid/Desktop/MapReduce/data/test/";
        REDUCE_NUMBER = 4;
    }

    public Map<TaskStatus, Set<MapTask>> initializeMapStore() {
        File[] inputFiles = new File(INPUT_DIRECTORY).listFiles();
        Map<TaskStatus, Set<MapTask>> result = new HashMap<>();
        result.put(TaskStatus.NOT_STARTED, new HashSet<>());
        result.put(TaskStatus.IN_PROGRESS, new HashSet<>());
        result.put(TaskStatus.COMPLETED, new HashSet<>());

        for(File inputFile: inputFiles) {
            if (inputFile.getName().substring(0, 5).equals("input")) {
                result.get(TaskStatus.NOT_STARTED).add(new MapTask(inputFile.getName(), TaskStatus.NOT_STARTED));
            }
        }

        return result;
    }

    public Map<TaskStatus, Set<Integer>> initializeReduceStore() {
        Map<TaskStatus, Set<Integer>> result = new HashMap<>();
        result.put(TaskStatus.NOT_STARTED, new HashSet<>());
        result.put(TaskStatus.IN_PROGRESS, new HashSet<>());
        result.put(TaskStatus.COMPLETED, new HashSet<>());

        for (int i  = 0; i < REDUCE_NUMBER; i++) {
                result.get(TaskStatus.NOT_STARTED).add(i);
        }

        return result;
    }

}

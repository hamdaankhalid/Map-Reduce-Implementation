package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.utils;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StoreInitializerTest {

    @Autowired
    private StoreInitializer storeInitializer;

    @Test
    public void itInitializesMapStoreBasedOnEnvDirectory() {
        Map<TaskStatus, Set<MapTask>> result = storeInitializer.initializeMapStore();

        List<String> expectedInputFiles = Arrays.asList( "input1", "input2", "input3", "input4", "input5" );

        List<String> mapFiles = new ArrayList<>();

        for (MapTask task: result.get(TaskStatus.NOT_STARTED)) {
            mapFiles.add(task.getFileName());
        }

        assert(expectedInputFiles.containsAll(mapFiles));
        assertEquals(5, result.get(TaskStatus.NOT_STARTED).size());
        assertEquals(0, result.get(TaskStatus.IN_PROGRESS).size());
        assertEquals(0, result.get(TaskStatus.COMPLETED).size());
    }

    @Test
    public void itInitializesReduceStoreBasedOnEnvReducers() {
        Map<TaskStatus, Set<Integer>> result = storeInitializer.initializeReduceStore();

        List<Integer> expectedNotStartedSet = new ArrayList<>();


        for (int i  = 0; i < 4; i++) {
            expectedNotStartedSet.add(i);
        }

        List<Integer> notStarted = new ArrayList<>(result.get(TaskStatus.NOT_STARTED));

        assert(expectedNotStartedSet.containsAll(notStarted));

        assertEquals(expectedNotStartedSet.size() , result.get(TaskStatus.NOT_STARTED).size());
        assertEquals(0, result.get(TaskStatus.IN_PROGRESS).size());
        assertEquals(0, result.get(TaskStatus.COMPLETED).size());
    }
}

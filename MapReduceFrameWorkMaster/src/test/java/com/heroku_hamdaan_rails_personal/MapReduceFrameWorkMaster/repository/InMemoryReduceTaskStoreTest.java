package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InMemoryReduceTaskStoreTest {
    @Autowired
    InMemoryReduceTaskStore store;

    @Test
    public void ifTasksAreNotFinishedItReturnsFalse() {
        HashMap<TaskStatus, Set<Integer>> initReduceTasks = new HashMap<>();
        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(1,2))
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initReduceTasks);

        assertFalse(store.areTasksFinished());

        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(1))
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList(2)));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initReduceTasks);

        assertFalse(store.areTasksFinished());

        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList())
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList(1, 2)));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initReduceTasks);

        assertFalse(store.areTasksFinished());
    }

    @Test
    public void ifTasksAreFinishedItReturnsTrue() {
        HashMap<TaskStatus, Set<Integer>> initReduceTasks = new HashMap<>();
        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList())
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList(1, 2)));
        store.setStatusToTask(initReduceTasks);

        assertTrue(store.areTasksFinished());
    }

    @Test
    public void itReturnsUnstartedTasks() {
        HashMap<TaskStatus, Set<Integer>> initReduceTasks = new HashMap<>();
        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(1))
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initReduceTasks);

        assertNotNull(store.getUnstartedTask());

        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList())
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList(1)));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initReduceTasks);

        assertNull( store.getUnstartedTask());

        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList())
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList(1)));
        store.setStatusToTask(initReduceTasks);

        assertNull(store.getUnstartedTask());
    }


    @Test
    public void itUpdatesStatus() {
        HashMap<TaskStatus, Set<Integer>> initReduceTasks = new HashMap<>();
        initReduceTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(1))
        );
        initReduceTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initReduceTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initReduceTasks);

        store.updateTaskStatus(1);

        assertFalse(initReduceTasks.get(TaskStatus.NOT_STARTED).contains(1));

        assertTrue(initReduceTasks.get(TaskStatus.IN_PROGRESS).contains(1));

        store.updateTaskStatus(1);

        assertFalse(initReduceTasks.get(TaskStatus.IN_PROGRESS).contains(1));

        assertTrue(initReduceTasks.get(TaskStatus.COMPLETED).contains(1));
    }
}

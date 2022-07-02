package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InMemoryMapTaskStoreTest {
    @Autowired
    InMemoryMapTaskStore store;

    @Test
    public void returnsUnstartedTaskIfPresent() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(new MapTask("file1", TaskStatus.NOT_STARTED)))
        );
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initMapTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initMapTasks);
        MapTask unStartedMapTask = store.getUnstartedTask();
        assertEquals(unStartedMapTask, new MapTask("file1", TaskStatus.NOT_STARTED));
    }

    @Test
    public void returnsNullAsUnstartedTaskIfNoUnstartedTask() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(TaskStatus.NOT_STARTED, new HashSet<>(Arrays.asList()));
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initMapTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initMapTasks);
        MapTask unStartedMapTask = store.getUnstartedTask();
        assertNull(unStartedMapTask);
    }

    @Test
    public void areTasksFinishedReturnsTrueIfNone() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(TaskStatus.NOT_STARTED, new HashSet<>());
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>());
        initMapTasks.put(TaskStatus.COMPLETED, new HashSet<>());
        store.setStatusToTask(initMapTasks);

        assertTrue(store.areTasksFinished());
    }

    @Test
    public void areTasksFinishedReturnsTrueIfAllCompleted() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(TaskStatus.NOT_STARTED, new HashSet<>(Arrays.asList()));
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initMapTasks.put(
                TaskStatus.COMPLETED,
                new HashSet<>(Arrays.asList(new MapTask("file1", TaskStatus.NOT_STARTED)))
        );
        store.setStatusToTask(initMapTasks);

        assertTrue(store.areTasksFinished());
    }

    @Test
    public void areTasksFinishedReturnsFalseIfNotStarted() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(new MapTask("file1", TaskStatus.NOT_STARTED)))
        );
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initMapTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initMapTasks);

        assertFalse(store.areTasksFinished());
    }

    @Test
    public void areTasksFinishedReturnsFalseIfInProgress() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList())
        );
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList(new MapTask("file1", TaskStatus.NOT_STARTED))));
        initMapTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initMapTasks);

        assertFalse(store.areTasksFinished());
    }

    @Test
    public void updatesTaskStatus() {
        HashMap<TaskStatus, Set<MapTask>> initMapTasks = new HashMap<>();
        initMapTasks.put(
                TaskStatus.NOT_STARTED,
                new HashSet<>(Arrays.asList(new MapTask("file1", TaskStatus.NOT_STARTED)))
        );
        initMapTasks.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList()));
        initMapTasks.put(TaskStatus.COMPLETED, new HashSet<>(Arrays.asList()));
        store.setStatusToTask(initMapTasks);

        MapTask toBeUpdated = new MapTask("file1", TaskStatus.NOT_STARTED);
        MapTask updatedTask = store.updateTaskStatus(toBeUpdated, TaskStatus.IN_PROGRESS);

        assertEquals(0, store.mapStatus().get(TaskStatus.NOT_STARTED).size());
        assertEquals(store.mapStatus().get(TaskStatus.IN_PROGRESS).stream().findFirst().get(), updatedTask);
        assertEquals(updatedTask.getStatus(), TaskStatus.IN_PROGRESS);

        MapTask toBeUpdatedAgain = new MapTask("file1", TaskStatus.IN_PROGRESS);
        MapTask movedToComplete = store.updateTaskStatus(toBeUpdatedAgain, TaskStatus.COMPLETED);

        assertEquals(0, store.mapStatus().get(TaskStatus.IN_PROGRESS).size());
        assertEquals(store.mapStatus().get(TaskStatus.COMPLETED).stream().findFirst().get(), movedToComplete);
        assertEquals(movedToComplete.getStatus(), TaskStatus.COMPLETED);
    }
}

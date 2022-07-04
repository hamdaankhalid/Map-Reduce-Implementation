package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.service;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto.StatusResponse;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto.TaskCompletedRequest;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto.TaskResponse;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository.IMapTaskStore;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository.IReduceTaskStore;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.utils.StoreInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class MasterService {

    @Autowired
    private IMapTaskStore mapTaskStore;

    @Autowired
    private IReduceTaskStore reduceTaskStore;

    @Autowired
    private StoreInitializer storeInit;

    @PostConstruct
    public void readyMapsAndReduces() {
        mapTaskStore.setStatusToTask(storeInit.initializeMapStore());
        reduceTaskStore.setStatusToTask(storeInit.initializeReduceStore());
    }

    public TaskResponse getTaskToExecute() {
        if (!mapTaskStore.areTasksFinished()) {
            MapTask unstartedTask = mapTaskStore.getUnstartedTask();
            if (unstartedTask != null) {
                mapTaskStore.updateTaskStatus(unstartedTask, TaskStatus.IN_PROGRESS);
            }
            return new TaskResponse(unstartedTask, null, false);
        } else if (!reduceTaskStore.areTasksFinished()) {
            Integer unstartedTask = reduceTaskStore.getUnstartedTask();
            if(unstartedTask != null) {
                reduceTaskStore.updateTaskStatus(unstartedTask);
            }
            return new TaskResponse(null, unstartedTask, false);
        } else {
            return new TaskResponse(null, null, true);
        }
    }

    public StatusResponse getStatus() {
        Map<TaskStatus, Set<MapTask>> mapTasks = mapTaskStore.mapStatus();
        Map<TaskStatus, Set<Integer>> reduceTasks = reduceTaskStore.reduceStatus();

        boolean isFinished = mapTaskStore.areTasksFinished() && reduceTaskStore.areTasksFinished();
        return new StatusResponse(isFinished, mapTasks, reduceTasks);
    }

    public void markTaskComplete(TaskCompletedRequest taskCompletedRequest) {
        if (taskCompletedRequest.getIsMapTask()) {
            mapTaskStore.updateTaskStatus(taskCompletedRequest.getMapTask(), TaskStatus.COMPLETED);
        } else {
            reduceTaskStore.updateTaskStatus(taskCompletedRequest.getReduceTask());
        }
    }
}

package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;

import java.util.Map;
import java.util.Set;

public interface IMapTaskStore {
    void setStatusToTask(Map<TaskStatus, Set<MapTask>> statusToTask);

    MapTask getUnstartedTask();

    boolean areTasksFinished();

    MapTask updateTaskStatus(MapTask taskToBeUpdated, TaskStatus status);

    Map<TaskStatus, Set<MapTask>> mapStatus();
}

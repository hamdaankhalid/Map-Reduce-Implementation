package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IReduceTaskStore {
    void setStatusToTask(Map<TaskStatus, Set<Integer>> statusToTask);

    Integer getUnstartedTask();

    boolean areTasksFinished();

    TaskStatus updateTaskStatus(int taskToBeUpdated);

    Map<TaskStatus, Set<Integer>> reduceStatus();

}

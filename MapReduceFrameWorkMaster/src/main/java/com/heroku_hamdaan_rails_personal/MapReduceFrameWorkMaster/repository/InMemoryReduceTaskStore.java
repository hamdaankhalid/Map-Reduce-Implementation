package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus.IN_PROGRESS;
import static com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus.NOT_STARTED;

@Primary
@Repository
public class InMemoryReduceTaskStore implements IReduceTaskStore {
    private Map<TaskStatus, Set<Integer>> statusToTask;

    public void setStatusToTask(Map<TaskStatus, Set<Integer>> statusToTask){
        this.statusToTask = statusToTask;
    }

    public Map<TaskStatus, Set<Integer>> reduceStatus() {
        return statusToTask;
    }

    public boolean areTasksFinished() {
        return statusToTask.get(NOT_STARTED).size() == 0 &&
                statusToTask.get(IN_PROGRESS).size() == 0;
    }

    public Integer getUnstartedTask() {
        if (statusToTask.get(NOT_STARTED).size() == 0) {
            return null;
        }
        return statusToTask.get(NOT_STARTED).stream().findFirst().get();
    }

    public TaskStatus updateTaskStatus(int taskToBeUpdated) {

        TaskStatus lastStatus = null;
        for (TaskStatus taskStatus: TaskStatus.values()) {
            if (statusToTask.get(taskStatus).contains(taskToBeUpdated)) {
                lastStatus = taskStatus;
                break;
            }
        }

        if (lastStatus == TaskStatus.COMPLETED) {
            return TaskStatus.COMPLETED;
        }

        statusToTask.get(lastStatus).remove(taskToBeUpdated);

        if (lastStatus == TaskStatus.NOT_STARTED) {
            statusToTask.get(TaskStatus.IN_PROGRESS).add((taskToBeUpdated));
            return TaskStatus.IN_PROGRESS;
        } else if (lastStatus == TaskStatus.IN_PROGRESS) {
            statusToTask.get(TaskStatus.COMPLETED).add((taskToBeUpdated));
            return TaskStatus.COMPLETED;
        }

        return TaskStatus.COMPLETED;
    }
}

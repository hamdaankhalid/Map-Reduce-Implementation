package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.repository;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

import static com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus.IN_PROGRESS;
import static com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus.NOT_STARTED;

@Primary
@Repository
public class InMemoryMapTaskStore implements IMapTaskStore {
    private Map<TaskStatus, Set<MapTask>> statusToTask;

    public Map<TaskStatus, Set<MapTask>> mapStatus() {
        return this.statusToTask;
    }

    public void setStatusToTask(Map<TaskStatus, Set<MapTask>> statusToTask) {
        this.statusToTask = statusToTask;
    }

    public MapTask getUnstartedTask() {
        if (statusToTask.get(NOT_STARTED).size() == 0) {
            return null;
        }
        MapTask taskToRun = statusToTask.get(NOT_STARTED).stream().findFirst().get();
        return taskToRun;
    }

    public boolean areTasksFinished() {
        return statusToTask.get(NOT_STARTED).size() == 0 &&
                statusToTask.get(IN_PROGRESS).size() == 0;
    }

    public MapTask updateTaskStatus(MapTask taskToBeUpdated, TaskStatus status) {

        statusToTask.get(taskToBeUpdated.getStatus()).remove(taskToBeUpdated);


        // remove the map task from the list corresponding to previous status, and add the new map task with updated
        // status to new status task list
        MapTask updatedTask = new MapTask(taskToBeUpdated.getFileName(), status);
        statusToTask.get(status).add(updatedTask);
        return updatedTask;
    }

}

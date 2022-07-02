package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;
import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.TaskStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatusResponse {
    private boolean isFinished;
    private Map<TaskStatus, Set<MapTask>> mapTasks;
    private Map<TaskStatus, Set<Integer>> reduceTasks;

    public StatusResponse(boolean isFinished, Map<TaskStatus, Set<MapTask>> mapTasks, Map<TaskStatus, Set<Integer>> reduceTasks) {
        this.isFinished = isFinished;
        this.mapTasks = mapTasks;
        this.reduceTasks = reduceTasks;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Map<TaskStatus, Set<MapTask>> getMapTasks() {
        return mapTasks;
    }

    public void setMapTasks(Map<TaskStatus, Set<MapTask>> mapTasks) {
        this.mapTasks = mapTasks;
    }

    public Map<TaskStatus, Set<Integer>> getReduceTasks() {
        return reduceTasks;
    }

    public void setReduceTasks(Map<TaskStatus, Set<Integer>> reduceTasks) {
        this.reduceTasks = reduceTasks;
    }
}

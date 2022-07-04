package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;

public class TaskResponse {
    private MapTask mapTask;
    private Integer reduceTask;
    private boolean isFinished;

    public TaskResponse(MapTask mapTask, Integer reduceTask, boolean isFinished) {
        this.mapTask = mapTask;
        this.reduceTask = reduceTask;
        this.isFinished = isFinished;
    }

    public MapTask getMapTask() {
        return mapTask;
    }

    public void setMapTask(MapTask mapTask) {
        this.mapTask = mapTask;
    }

    public Integer getReduceTask() {
        return reduceTask;
    }

    public void setReduceTask(int reduceTask) {
        this.reduceTask = reduceTask;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}

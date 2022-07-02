package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;

public class TaskResponse {
    // should return either a map job or a reduce job // or say that all jobs are over
    private MapTask mapTask;
    private Integer reduceTask;
    private boolean isFinished;

    public MapTask getMapTask() {
        return mapTask;
    }

    public void setMapTask(MapTask mapTask) {
        this.mapTask = mapTask;
    }

    public int getReduceTask() {
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

    public TaskResponse(MapTask mapTask, Integer reduceTask, boolean isFinished) {
        this.mapTask = mapTask;
        this.reduceTask = reduceTask;
        this.isFinished = isFinished;
    }
}

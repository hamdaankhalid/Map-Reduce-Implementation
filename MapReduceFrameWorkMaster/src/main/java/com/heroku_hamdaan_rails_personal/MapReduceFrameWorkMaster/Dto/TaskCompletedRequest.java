package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.Dto;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity.MapTask;

public class TaskCompletedRequest {
    private boolean isMapTask;
    private MapTask mapTask;
    private Integer reduceTask;

    public TaskCompletedRequest(boolean isMapTask, MapTask mapTask, Integer reduceTask) {
        this.isMapTask = isMapTask;
        this.mapTask = mapTask;
        this.reduceTask = reduceTask;
    }

    public boolean isMapTask() {
        return isMapTask;
    }

    public void setMapTask(boolean mapTask) {
        isMapTask = mapTask;
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

    public void setReduceTask(Integer reduceTask) {
        this.reduceTask = reduceTask;
    }
}

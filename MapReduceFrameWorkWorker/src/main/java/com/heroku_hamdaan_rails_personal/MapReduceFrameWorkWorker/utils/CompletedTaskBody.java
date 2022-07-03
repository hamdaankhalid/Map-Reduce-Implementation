package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils;

public class CompletedTaskBody {
    private boolean isMapTask;
    private MapTask mapTask;
    private Integer reduceTask;

    public CompletedTaskBody(boolean isMapTask, MapTask mapTask, Integer reduceTask) {
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

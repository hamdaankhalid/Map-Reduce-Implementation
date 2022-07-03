package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Response;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.MapTask;

public class RequestTaskResponse {
    // should return either a map job or a reduce job // or say that all jobs are over
    private MapTask mapTask;
    private Integer reduceTask;
    private boolean isFinished;

    public RequestTaskResponse(MapTask mapTask, Integer reduceTask, boolean isFinished) {
        this.mapTask = mapTask;
        this.reduceTask = reduceTask;
        this.isFinished = isFinished;
    }

    public RequestTaskResponse() {}

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

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}

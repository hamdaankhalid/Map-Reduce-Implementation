package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.Response;

import com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils.MapTask;

public class RequestTaskResponse {
    // should return either a map job or a reduce job // or say that all jobs are over
    private MapTask mapTask;
    private String reduceTask;
    private boolean isFinished;

    public MapTask getMapTask() {
        return mapTask;
    }

    public void setMapTask(MapTask mapTask) {
        this.mapTask = mapTask;
    }

    public String getReduceTask() {
        return reduceTask;
    }

    public void setReduceTask(String reduceTask) {
        this.reduceTask = reduceTask;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public RequestTaskResponse(MapTask mapTask, String reduceTask, boolean isFinished) {
        this.mapTask = mapTask;
        this.reduceTask = reduceTask;
        this.isFinished = isFinished;
    }
}
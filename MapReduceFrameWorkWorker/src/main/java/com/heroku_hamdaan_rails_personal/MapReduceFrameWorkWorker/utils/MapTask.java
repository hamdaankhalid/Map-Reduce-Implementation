package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils;

public class MapTask {
    private String fileName;
    private TaskStatus status;
    private int mapTaskId;

    public MapTask(String filename, TaskStatus status, int mapTaskId) {
        this.fileName = filename;
        this.status = status;
        this.mapTaskId = mapTaskId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getMapTaskId() {
        return mapTaskId;
    }

    public void setMapTaskId(int mapTaskId) {
        this.mapTaskId = mapTaskId;
    }
}

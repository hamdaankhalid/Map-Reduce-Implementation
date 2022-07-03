package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkWorker.utils;

public class MapTask {
    private String fileName;
    private TaskStatus status;

    public MapTask(String filename, TaskStatus status) {
        this.fileName = filename;
        this.status = status;
    }

    public MapTask() {}

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
}

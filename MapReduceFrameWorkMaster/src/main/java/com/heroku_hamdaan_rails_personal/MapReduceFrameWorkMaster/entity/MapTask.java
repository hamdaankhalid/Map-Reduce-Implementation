package com.heroku_hamdaan_rails_personal.MapReduceFrameWorkMaster.entity;

import org.springframework.scheduling.config.Task;

import java.util.Objects;

public class MapTask {
    private String fileName;
    private TaskStatus status;

    public MapTask(String filename, TaskStatus status) {
        this.fileName = filename;
        this.status = status;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof MapTask)) {
            return false;
        }

        return fileName.equals(((MapTask) obj).fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }

    @Override
    public String toString() {
        return "( "+ fileName + ", " + status +")";
    }
}

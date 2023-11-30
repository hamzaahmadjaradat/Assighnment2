package com.example.taskmanager;


public class Task  {
    private String task;
    private String status;

    public Task(String task, String status) {
        this.task = task;
        this.status = status;
    }

    @Override
    public String toString() {
        return "" + task+" "+status;
    }


    public String getTask() {
        return task.trim();
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

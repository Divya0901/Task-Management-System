package com.dg.Task_Management_System.dto;

import com.dg.Task_Management_System.entity.TaskDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private String status;
    private TaskDetails task;

    public ResponseDto() {
    }

    public ResponseDto(String status) {
        this.status = status;
    }

    public ResponseDto(String status, TaskDetails task) {
        this.status = status;
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskDetails getTask() {
        return task;
    }

    public void setTask(TaskDetails task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "message='" + status + '\'' +
                ", taskDetails=" + task +
                '}';
    }
}

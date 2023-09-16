package com.dg.Task_Management_System.dto;

import com.dg.Task_Management_System.entity.StatusOfTask;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class UpdateTaskDetailsDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String taskTitle;

    private String description;
    
    private Date assignedDate;
    private StatusOfTask statusOfTask;

    public UpdateTaskDetailsDto(String taskTitle, String description, Date assignedDate, StatusOfTask statusOfTask) {
        this.taskTitle = taskTitle;
        this.description = description;
        this.assignedDate = assignedDate;
        this.statusOfTask = statusOfTask;
    }

    public UpdateTaskDetailsDto() {
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public StatusOfTask getStatusOfTask() {
        return statusOfTask;
    }

    public void setStatusOfTask(StatusOfTask statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    @Override
    public String toString() {
        return "UpdateTaskDetails{" +
                "taskTitle='" + taskTitle + '\'' +
                ", description='" + description + '\'' +
                ", assignedDate=" + assignedDate +
                ", statusOfTask=" + statusOfTask +
                '}';
    }
}

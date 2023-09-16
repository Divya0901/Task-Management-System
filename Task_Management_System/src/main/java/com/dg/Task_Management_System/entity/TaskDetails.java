package com.dg.Task_Management_System.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_details", schema = "task_management")
public class TaskDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    private String title;

    private String description;

    private Date assignedDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOfTask statusOfTask;

    public TaskDetails(int id, String title, String description, Date assignedDate, StatusOfTask statusOfTask) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedDate = assignedDate;
        this.statusOfTask = statusOfTask;
    }

    public TaskDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public StatusOfTask getStatusOfTask() {
        return statusOfTask;
    }

    public void setStatusOfTask(StatusOfTask statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    @Override
    public String toString() {
        return "TaskDetails{" +
                "taskId=" + id +
                ", taskTitle='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assignedDate=" + assignedDate +
                ", statusOfTask=" + statusOfTask +
                '}';
    }
}

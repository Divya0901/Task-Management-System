package com.dg.Task_Management_System.service;

import com.dg.Task_Management_System.dto.TaskDto;
import com.dg.Task_Management_System.dto.UpdateTaskDetailsDto;
import com.dg.Task_Management_System.entity.StatusOfTask;
import com.dg.Task_Management_System.entity.TaskDetails;
import com.dg.Task_Management_System.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public String validateTaskRequest(TaskDto taskDto) {
        StringBuilder message = new StringBuilder();
        if (ObjectUtils.isEmpty(taskDto)) {
            return "Invalid request";
        }
        if (!StringUtils.hasText(taskDto.getTaskTitle())) {
            message.append("title ");
        }
        if (!StringUtils.hasText(taskDto.getTaskDescription())) {
            if (StringUtils.hasText(message))
                message.append("and ");
            message.append("description ");
        }
        if (StringUtils.hasText(message)) {
            message.append("can not be empty");
            return message.toString();
        }
        return null;
    }

    public TaskDetails saveTaskDetails(TaskDto taskDto) {
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTitle(taskDto.getTaskTitle());
        taskDetails.setDescription(taskDto.getTaskDescription());
        taskDetails.setAssignedDate(new Date());
        taskDetails.setStatusOfTask(StatusOfTask.NOT_COMPLETED);
        return taskRepository.save(taskDetails);
    }

    public TaskDetails getTaskDetailsByTaskId(Integer taskId) {
        Optional<TaskDetails> taskDetails = taskRepository.findById(taskId);
        if(taskDetails.isPresent()) {
            return taskDetails.get();
        }
        else
            return null;
    }

    public List<TaskDetails> getAllTasksDetails() {
        return taskRepository.findAll();
    }

    public List<TaskDetails> deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
        List<TaskDetails> taskDetailsList = getAllTasksDetails();
        return taskDetailsList;
    }

    public TaskDetails updateTaskDetails(Integer id, UpdateTaskDetailsDto updateTaskDetailsDto) {
        TaskDetails taskDetails = getTaskDetailsByTaskId(id);
        if(taskDetails == null) {
            return null;
        }
        else {
//            BeanUtils.copyProperties(updateTaskDetailsDto, taskDetails);
            if(updateTaskDetailsDto.getAssignedDate() != null)
                taskDetails.setAssignedDate(updateTaskDetailsDto.getAssignedDate());
            if(updateTaskDetailsDto.getTaskTitle() != null)
                taskDetails.setTitle(updateTaskDetailsDto.getTaskTitle());
            if(updateTaskDetailsDto.getDescription() != null)
                taskDetails.setDescription(updateTaskDetailsDto.getDescription());
            if(updateTaskDetailsDto.getAssignedDate() != null)
                taskDetails.setAssignedDate(updateTaskDetailsDto.getAssignedDate());
            if(updateTaskDetailsDto.getStatusOfTask() != null)
                taskDetails.setStatusOfTask(updateTaskDetailsDto.getStatusOfTask());

            taskRepository.save(taskDetails);
            return taskDetails;
        }
    }

    public TaskDetails completedTask(Integer id) {
        Optional<TaskDetails> optionalTaskDetails = taskRepository.findById(id);
        if (optionalTaskDetails.isPresent()) {
            TaskDetails taskDetails = optionalTaskDetails.get();
            if (taskDetails.getEndDate() == null) {
                taskDetails.setEndDate(new Date());
                taskDetails.setStatusOfTask(StatusOfTask.COMPLETED);
            } else {
                taskDetails.setEndDate(null);
                taskDetails.setStatusOfTask(StatusOfTask.NOT_COMPLETED);
            }
            taskRepository.save(taskDetails);
            return taskDetails;
        }
        return null;
    }
}

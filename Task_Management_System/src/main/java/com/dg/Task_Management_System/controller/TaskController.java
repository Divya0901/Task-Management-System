package com.dg.Task_Management_System.controller;

import com.dg.Task_Management_System.dto.ResponseDto;
import com.dg.Task_Management_System.dto.TaskDto;
import com.dg.Task_Management_System.dto.UpdateTaskDetailsDto;
import com.dg.Task_Management_System.entity.TaskDetails;
import com.dg.Task_Management_System.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;


    @CrossOrigin(origins = "http://127.0.0.1:63342")
    @PostMapping("/saveTaskDetails")
    public ResponseEntity<ResponseDto> saveTaskDetails(@RequestBody TaskDto taskDto) {
        String message = taskService.validateTaskRequest(taskDto);
        if (!StringUtils.hasText(message)) {
            TaskDetails taskDetails = taskService.saveTaskDetails(taskDto);
            return new ResponseEntity<>(new ResponseDto("Task Created Successfully", taskDetails), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("Unable to create task"), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://127.0.0.1:63342")
    @GetMapping("/getTaskDetailsByTaskId/{taskId}")
    public ResponseEntity<TaskDetails> getTaskDetailsByTaskId(@PathVariable Integer taskId) {
        TaskDetails taskDetails = taskService.getTaskDetailsByTaskId(taskId);
        return new ResponseEntity<>(taskDetails, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://127.0.0.1:63342")
    @GetMapping("/getAllTasksDetails")
    public ResponseEntity<List<TaskDetails>> getAllTasksDetails() {
        List<TaskDetails> taskDetailsList = taskService.getAllTasksDetails();
        return new ResponseEntity(taskDetailsList, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://127.0.0.1:63342")
    @DeleteMapping("/deleteTaskById/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Integer id) {
        List<TaskDetails> taskDetailsList = taskService.deleteTaskById(id);
        return new ResponseEntity<>(taskDetailsList, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://127.0.0.1:63342")
    @PatchMapping ("/updateTaskDetails/{id}")
    public ResponseEntity<TaskDetails> updateTaskDetails(@PathVariable Integer id, @RequestBody UpdateTaskDetailsDto updateTaskDetailsDto) {
        TaskDetails taskDetails = taskService.updateTaskDetails(id,updateTaskDetailsDto);
        return new ResponseEntity<>(taskDetails, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://127.0.0.1:63342")
    @PatchMapping ("/completedTask/{id}")
    public ResponseEntity<TaskDetails> completedTask(@PathVariable Integer id) {
        TaskDetails taskDetails = taskService.completedTask(id);
        return new ResponseEntity<>(taskDetails, HttpStatus.OK);
    }
}

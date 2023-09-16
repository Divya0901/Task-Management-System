package com.dg.Task_Management_System.repository;

import com.dg.Task_Management_System.entity.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskDetails, Integer> {

}

package com.qnit18.service;

import com.qnit18.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE",url = "http://localhost:5002/api/tasks")
public interface TaskService {

    @GetMapping("/{id}")
    TaskDto getTaskById(@PathVariable Long id,
                                               @RequestHeader("Authorization") String jwt) throws Exception ;

    @PutMapping("/{taskId}/complete")
    TaskDto completeTask(
            @PathVariable Long taskId) throws Exception;
}

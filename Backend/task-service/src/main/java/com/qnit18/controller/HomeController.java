package com.qnit18.controller;

import com.qnit18.dto.UserDto;
import com.qnit18.model.Task;
import com.qnit18.model.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/tasks2")
    public ResponseEntity<String> getAssignedUsersTask() throws Exception {
        return new ResponseEntity<>("Welcome to task service", HttpStatus.OK);
    }
}

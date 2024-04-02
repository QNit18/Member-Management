package com.qnit18.controller;

import com.qnit18.dto.UserDto;
import com.qnit18.model.Task;
import com.qnit18.model.TaskStatus;
import com.qnit18.service.TaskService;
import com.qnit18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task request,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);

        Task createdTask = taskService.createTask(request, userDto.getRole());

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.getTaskById(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUsersTask(
            @RequestParam(required = false) TaskStatus status,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);

        List<Task> tasks = taskService.assignedUserTask(userDto.getId(), status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTask(
            @RequestParam(required = false) TaskStatus status,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);

        List<Task> tasks = taskService.getAllTask(status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(
            @PathVariable Long taskId,
            @PathVariable Long userId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.assignedToUser(userId, taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @RequestBody Task request,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);

        Task task = taskService.updateTask(taskId, request, userDto.getId());

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(
            @PathVariable Long taskId) throws Exception {

        Task task = taskService.completeTask(taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId) throws Exception {

        taskService.deleteTask(taskId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

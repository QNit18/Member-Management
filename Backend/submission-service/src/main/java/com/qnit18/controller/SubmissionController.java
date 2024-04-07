package com.qnit18.controller;

import com.qnit18.dto.UserDto;
import com.qnit18.model.Submission;
import com.qnit18.service.SubmissionService;
import com.qnit18.service.SubmissionServiceImpl;
import com.qnit18.service.TaskService;
import com.qnit18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Submission> submissTask(
            @RequestParam Long task_id,
            @RequestParam String github_link,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(task_id, github_link, user.getId(), jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getAllTaskSubmissions();
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllTaskSubmissionsByTaskId(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getTaskSubmissionsByTaskId(taskId);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<Submission> acceptOrDeclineSubmission(
            @PathVariable Long taskId,
            @RequestParam("status") String status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(taskId,status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

}

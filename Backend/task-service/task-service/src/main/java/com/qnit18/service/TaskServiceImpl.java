package com.qnit18.service;

import com.qnit18.model.Task;
import com.qnit18.model.TaskStatus;
import com.qnit18.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if (!requesterRole.equals(("ROLE_ADMIN"))){
            throw new Exception("Only ADMIN can create task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(() -> new Exception("Task not found with id: "+ id));
    }

    @Override
    public List<Task> getAllTask(TaskStatus status) {
        List<Task> allTask = taskRepository.findAll();

        List<Task> filteredTasks = allTask.stream().filter(
                task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).toList();
        return filteredTasks;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        return null;
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) {
        return null;
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        return null;
    }
}

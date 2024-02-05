package com.example.demo.task;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskByIDforWorkers(int id) {
        return taskRepository.findByIdWithWorkers(id);
    }

    public Task addTask(Task task){
        task.setStatus(Status.ACTIVE);
        return taskRepository.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public List<Task> findByStatus(Status status){
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> changeStatus(int taskID, Status newStatus){
        Optional<Task> optionalTask = taskRepository.findById(taskID);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(newStatus);
            return Optional.of(taskRepository.save(task));
        } else {
            return Optional.empty();
        }
    }

    public void deleteTask(int taskId){
        taskRepository.deleteById(taskId);
    }

    public Task getTaskByID(int id) {
        return  taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void updateTask(int id, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);

        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());

            taskRepository.save(existingTask);
        }
    }
}

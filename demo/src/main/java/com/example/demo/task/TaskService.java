package com.example.demo.task;

import com.example.demo.aspect.HandleError;
import com.example.demo.aspect.TrackUserAction;
import com.example.demo.task.enumeration.Status;
import com.example.demo.task.observer.ObserverService;
import com.example.demo.task.observer.TaskLogger;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private ObserverService observerService;

    @Autowired
    public TaskService(TaskRepository taskRepository, ObserverService observerService) {
        this.taskRepository = taskRepository;
        this.observerService = observerService;
    }

    @PostConstruct
    public void init() {

        // Зарегистрируем наблюдателя (TaskLogger) в сервисе наблюдателя после инициализации зависимостей
        this.observerService.registerObserver(new TaskLogger());
    }

    public Task getTaskByIDforWorkers(int id) {
        return taskRepository.findByIdWithWorkers(id);
    }

    @TrackUserAction
    public Task addTask(Task task){
        task.setStatus(Status.ACTIVE);

        return taskRepository.save(task);
    }

    @TrackUserAction
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    @TrackUserAction
    public List<Task> findByStatus(Status status){
        return taskRepository.findByStatus(status);
    }


    @TrackUserAction
    public Optional<Task> changeStatus(int taskID, Status newStatus){

        Optional<Task> optionalTask = taskRepository.findById(taskID);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(newStatus);

            observerService.notifyObservers(task);

            return Optional.of(taskRepository.save(task));
        } else {
            return Optional.empty();
        }


    }

    @TrackUserAction
    public void deleteTask(int taskId){
        taskRepository.deleteById(taskId);
    }

    @TrackUserAction
    @HandleError
    public Task getTaskByID(int id) {
        return  taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @TrackUserAction
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
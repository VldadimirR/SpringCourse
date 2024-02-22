package com.example.demo.work;

import com.example.demo.aspect.HandleError;
import com.example.demo.aspect.TrackUserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.aspect.HandleError;
import com.example.demo.aspect.TrackUserAction;
import com.example.demo.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @TrackUserAction
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }


    @TrackUserAction
    @HandleError
    public Worker findById(int id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found"));
    }

    @TrackUserAction
    public Worker save(Worker reader) {
        return workerRepository.save(reader);
    }

    @TrackUserAction
    public void assign(int id, Task selectTask) {
        Worker worker = findById(id);
        worker.setOwner(selectTask);
        workerRepository.save(worker);
    }

    @TrackUserAction
    public void release(int id) {
        Worker worker = findById(id);
        worker.setOwner(null);
        workerRepository.save(worker);
    }
}
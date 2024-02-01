package com.example.demo;

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

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(int id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found"));
    }

    public Worker save(Worker reader) {
        return workerRepository.save(reader);
    }

    public void assign(int id, Task selectTask) {
        Worker worker = findById(id);
        worker.setOwner(selectTask);
        workerRepository.save(worker);
    }

    public void release(int id) {
        Worker worker = findById(id);
        worker.setOwner(null);
        workerRepository.save(worker);
    }
}

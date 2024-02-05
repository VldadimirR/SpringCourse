package com.example.demo.work;


import com.example.demo.task.Task;
import com.example.demo.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/workers")
public class WorkController {

    private final WorkerService workerService;

    private final TaskService taskService;


    public WorkController(WorkerService workerService, TaskService taskService) {
        this.workerService = workerService;
        this.taskService = taskService;
    }

    @RequestMapping(value = "/{id}/assign", method = {RequestMethod.PATCH, RequestMethod.POST})
    public String assign(@PathVariable int id,
                         @ModelAttribute("task") Task selectTask){
        workerService.assign(id, selectTask);

        return "redirect:/workers/" + id ;
    }

    @RequestMapping(value = "/{id}/release", method = {RequestMethod.PATCH, RequestMethod.POST})
    public String release(@PathVariable int id){
        workerService.release(id);
        return "redirect:/workers/" + id;
    }

    @GetMapping("/{id}")
    public String getWorker(@PathVariable int id, Model model){
        Worker worker = workerService.findById(id);
        List<Task> tasks = taskService.getAllTask();
        Task taskOwner = null;
        if (worker.getOwner() != null) {
            taskOwner = taskService.getTaskByID(worker.getOwner().getId());
        }
        model.addAttribute("taskOwner", taskOwner);

        model.addAttribute("tasks", tasks);
        model.addAttribute("worker", worker);
        model.addAttribute("task", new Task());
        return "worker" ;
    }

    @GetMapping
    public String getWorkers(Model model){
        List<Worker> workers = workerService.findAll();
        model.addAttribute("workers", workers);
        return "workers";
    }

    @PostMapping("/addWorker")
    public String addWorker(@ModelAttribute Worker worker){
        workerService.save(worker);

        return "redirect:/workers";
    }

}

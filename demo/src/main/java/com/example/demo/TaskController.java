package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String getTasks(@RequestParam(name = "status", defaultValue = "ALL")
                           Status status,
                           Model model){
        List<Task> tasks;

        if (status.equals(Status.ALL)) {
            tasks = taskService.getAllTask();
        } else {
            tasks = taskService.findByStatus(status);
            model.addAttribute("status", status);
        }

        model.addAttribute("tasks", tasks);
        return "tasks";
    }


    @PostMapping("/addTasks")
    public String addTask(@ModelAttribute Task task,
                          RedirectAttributes redirectAttributes){
        taskService.addTask(task);

        redirectAttributes.addFlashAttribute("successMessage", "Task created successfully!");

        return "redirect:/tasks";
    }

    @PostMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable int id,
                               @RequestParam Status newStatus,
                               RedirectAttributes redirectAttributes) {
        Optional<Task> updatedTask = taskService.changeStatus(id, newStatus);

        if (updatedTask.isPresent()) {
            redirectAttributes.addFlashAttribute("changeMessage", "task has been successfully changed!");
            return "redirect:/tasks";
        }
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id, RedirectAttributes redirectAttributes){
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("deleteMessage", "the task was successfully deleted!");
        return "redirect:/tasks";
    }

}
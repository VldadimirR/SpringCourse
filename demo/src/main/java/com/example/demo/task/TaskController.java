package com.example.demo.task;

import com.example.demo.task.enumeration.Status;
import com.example.demo.task.enumeration.Type;
import com.example.demo.task.factory.MyTaskFactory;
import com.example.demo.task.factory.TaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final MyTaskFactory myTaskFactory;


    @Autowired
    public TaskController(TaskService taskService, MyTaskFactory myTaskFactory) {
        this.taskService = taskService;
        this.myTaskFactory = myTaskFactory;
    }


    @GetMapping()
    public ModelAndView getTasks(@RequestParam(name = "status", defaultValue = "ALL")
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
        return new ModelAndView("tasks");
    }


    @GetMapping("/{id}")
    public String getTask(@PathVariable int id, Model model){

        Task task = taskService.getTaskByIDforWorkers(id);

        model.addAttribute("task", task);
        model.addAttribute("workers", task.getWorkers());

        return "task";
    }


    @PostMapping("/addTasks")
    public String addTask(@ModelAttribute TaskDTO taskDTO,
                          RedirectAttributes redirectAttributes){

        Type type = taskDTO.type();
        TaskFactory taskFactory = myTaskFactory.getTaskFactory(type);
        Task task = taskFactory.createTask(taskDTO.description(), taskDTO.type());
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


    @PutMapping("/{id}")
    public String updateTask(@PathVariable int id, Task task){

        taskService.updateTask(id, task);

        return "redirect:/task" + id;
    }



    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id, RedirectAttributes redirectAttributes){
        taskService.deleteTask(id);

        redirectAttributes.addFlashAttribute("deleteMessage", "the task was successfully deleted!");

        return "redirect:/tasks";
    }

}
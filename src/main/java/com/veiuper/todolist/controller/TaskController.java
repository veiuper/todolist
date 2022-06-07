package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.Task;
import com.veiuper.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        List<Task> taskList = taskService.getAll();
        model.addAttribute("taskList", taskList);
        model.addAttribute("taskSize", taskList.size());
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable long id) {
        taskService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteAll() {
        taskService.deleteAll();
        return "redirect:/";
    }

    @RequestMapping("/switchTaskStatus/{id}")
    public String switchTaskStatus(@PathVariable long id) throws Exception {
        taskService.switchTaskStatus(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/";
    }
}

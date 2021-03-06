package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.TasklistEntity;
import com.veiuper.todolist.service.TasklistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TasklistController {
    private final TasklistService tasklistService;

    public TasklistController(TasklistService tasklistService) {
        this.tasklistService = tasklistService;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        List<TasklistEntity> tasklistEntityList = tasklistService.getAll();
        model.addAttribute("tasklistList", tasklistEntityList);
        model.addAttribute("tasklistSize", tasklistEntityList.size());
        return "index";
    }

    @PostMapping("/add")
    public String addTasklist(@ModelAttribute TasklistEntity tasklistEntity) {
        tasklistService.save(tasklistEntity);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTasklist(@PathVariable Long id) {
        tasklistService.delete(id);
        return "redirect:/";
    }
}

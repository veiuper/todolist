package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.TaskEntity;
import com.veiuper.todolist.model.TasklistEntity;
import com.veiuper.todolist.service.TaskService;
import com.veiuper.todolist.service.TasklistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final TasklistService tasklistService;

    public TaskController(TaskService taskService, TasklistService tasklistService) {
        this.taskService = taskService;
        this.tasklistService = tasklistService;
    }

    @GetMapping("/tasklist/{tasklistId}/tasks")
    public String getAll(Model model, @PathVariable Long tasklistId) {
        TasklistEntity tasklistEntity = tasklistService.getById(tasklistId);
        Set<TaskEntity> taskEntityList = tasklistEntity.getTasks();
        model.addAttribute("tasklist", tasklistEntity);
        model.addAttribute("taskList", taskEntityList);
        model.addAttribute("taskSize", taskEntityList.size());
        return "tasklist";
    }

    @PostMapping("/tasklist/{tasklistId}/add")
    public String addTask(@ModelAttribute TaskEntity taskEntity, @PathVariable Long tasklistId) {
        taskEntity.setTasklist(tasklistService.getById(tasklistId));
        taskService.save(taskEntity);
        return "redirect:/tasklist/{tasklistId}/tasks";
    }
    @RequestMapping("/tasklist/{tasklistId}/delete/{taskId}")
    public String deleteTask(@PathVariable String tasklistId, @PathVariable Long taskId) {
        taskService.delete(taskId);
        return "redirect:/tasklist/{tasklistId}/tasks";
    }

//    @RequestMapping("/tasklist/{id}/tasks/delete")
//    public String deleteAll() {
//        taskService.deleteAll();
//        return "tasklist";
//    }
//
//    @RequestMapping("/tasklist/{id}/task/reversTaskStatus/{id}")
//    public String reversTaskStatus(@PathVariable Long id) throws Exception {
//        taskService.reversTaskStatus(id);
//        return "redirect:/tasklist";
//    }
}

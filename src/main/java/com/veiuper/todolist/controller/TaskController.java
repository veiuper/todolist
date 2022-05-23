package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.TaskEntity;
import com.veiuper.todolist.model.TasklistEntity;
import com.veiuper.todolist.service.TaskService;
import com.veiuper.todolist.service.TasklistService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TaskController {
    TaskService taskService;
    TasklistService tasklistService;

    @GetMapping("/tasklist/{tasklistId}/tasks")
    public String getAll(Model model, @PathVariable Long tasklistId) {
        TasklistEntity tasklistEntity = tasklistService.getById(tasklistId);
        Set<TaskEntity> taskEntityList = taskService.findByTasklistEntityIdOrderByExecutedAscIdAsc(tasklistId);
        model.addAttribute("tasklist", tasklistEntity);
        model.addAttribute("taskList", taskEntityList);
        return "tasklist";
    }

    @PostMapping("/tasklist/{tasklistId}/add")
    public String addTask(@ModelAttribute TaskEntity taskEntity, @PathVariable Long tasklistId) {
        taskEntity.setTasklistEntity(tasklistService.getById(tasklistId));
        taskService.save(taskEntity);
        return "redirect:/tasklist/{tasklistId}/tasks";
    }
    @RequestMapping("/tasklist/{tasklistId}/delete/{taskId}")
    public String deleteTask(@PathVariable String tasklistId, @PathVariable Long taskId) {
        taskService.delete(taskId);
        return "redirect:/tasklist/{tasklistId}/tasks";
    }

    @RequestMapping("/tasklist/{tasklistId}/switchTaskStatus/{taskId}")
    public String reversTaskStatus(@PathVariable Long tasklistId, @PathVariable Long taskId) throws Exception {
        taskService.switchTaskStatus(taskId);
        return "redirect:/tasklist/{tasklistId}/tasks";
    }
}

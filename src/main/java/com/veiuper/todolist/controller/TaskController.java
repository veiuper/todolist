package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.entity.TaskEntity;
import com.veiuper.todolist.model.entity.TasklistEntity;
import com.veiuper.todolist.service.TaskService;
import com.veiuper.todolist.service.TasklistService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TaskController {
    TaskService taskService;
    TasklistService tasklistService;

    @GetMapping("/tasklist/tasks")
    public String getAll(Model model, @RequestParam @Min(0) Long tasklistId) {
        TasklistEntity tasklistEntity = tasklistService.getById(tasklistId);
        Set<TaskEntity> taskEntitySet = taskService.findByTasklistEntityIdOrderByExecutedAscIdAsc(tasklistId);
        model.addAttribute("tasklist", tasklistEntity);
        model.addAttribute("taskList", taskEntitySet);
        return "tasklist";
    }

    @PostMapping("/tasklist/add")
    public String addTask(
            @ModelAttribute(name = "taskentity") @Valid TaskEntity taskEntity,
            @RequestParam @Min(0) Long tasklistId
    ) {
        taskEntity.setTasklistEntity(tasklistService.getById(tasklistId));
        taskService.save(taskEntity);
        String redirectTo = "/tasklist/tasks?tasklistId=" + tasklistId;
        return "redirect:" + redirectTo;
    }
    @PostMapping("/tasklist/delete")
    public String deleteTask(@RequestParam @Min(0) Long tasklistId, @RequestParam @Min(0) Long taskId) {
        taskService.delete(taskId);
        String redirectTo = "/tasklist/tasks?tasklistId=" + tasklistId;
        return "redirect:" + redirectTo;
    }

    @PostMapping("/tasklist/switchTaskStatus")
    public String reversTaskStatus(
            @RequestParam @Min(0) Long tasklistId,
            @RequestParam @Min(0) Long taskId
    ) throws Exception {
        taskService.switchTaskStatus(taskId);
        String redirectTo = "/tasklist/tasks?tasklistId=" + tasklistId;
        return "redirect:" + redirectTo;
    }
}

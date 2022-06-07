package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.entity.TaskEntity;
import com.veiuper.todolist.model.entity.TasklistEntity;
import com.veiuper.todolist.service.TaskService;
import com.veiuper.todolist.service.TasklistService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.Objects;
import java.util.Set;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TaskController {
    TaskService taskService;
    TasklistService tasklistService;

    @GetMapping("/tasklist/tasks")
    public String getAll(Model model, @RequestParam @Min(0) Long tasklistId, Principal principal) {
        TasklistEntity tasklistEntity = tasklistService.getById(tasklistId);
        if (!Objects.equals(tasklistEntity.getUser().getEmail(), principal.getName())) {
            throw new AccessDeniedException("Доступ запрещен.");
        }
        Set<TaskEntity> taskEntitySet = taskService.findByTasklistEntityIdOrderByExecutedAscIdAsc(tasklistId);
        model.addAttribute("tasklist", tasklistEntity);
        model.addAttribute("taskList", taskEntitySet);
        return "tasklist";
    }

    @PostMapping("/tasklist/add")
    public String addTask(
            @ModelAttribute(name = "taskentity") @Valid TaskEntity taskEntity,
            @RequestParam @Min(0) Long tasklistId,
            Principal principal
    ) {
        if (!Objects.equals(taskEntity.getTasklistEntity().getUser().getEmail(), principal.getName())) {
            throw new AccessDeniedException("Доступ запрещен.");
        }
        taskEntity.setTasklistEntity(tasklistService.getById(tasklistId));
        taskService.save(taskEntity);
        String redirectTo = "/tasklist/tasks?tasklistId=" + tasklistId;
        return "redirect:" + redirectTo;
    }

    @PostMapping("/tasklist/delete")
    public String deleteTask(
            @RequestParam @Min(0) Long tasklistId,
            @RequestParam @Min(0) Long taskId,
            Principal principal
    ) {
        TasklistEntity tasklistEntity = tasklistService.getById(taskId);
        if (!Objects.equals(tasklistEntity.getUser().getEmail(), principal.getName())) {
            throw new AccessDeniedException("Доступ запрещен.");
        }
        taskService.delete(taskId);
        String redirectTo = "/tasklist/tasks?tasklistId=" + tasklistId;
        return "redirect:" + redirectTo;
    }

    @PostMapping("/tasklist/switchTaskStatus")
    public String reversTaskStatus(
            @RequestParam @Min(0) Long tasklistId,
            @RequestParam @Min(0) Long taskId,
            Principal principal
    ) throws Exception {
        TasklistEntity tasklistEntity = tasklistService.getById(tasklistId);
        if (!Objects.equals(tasklistEntity.getUser().getEmail(), principal.getName())) {
            throw new AccessDeniedException("Доступ запрещен.");
        }
        taskService.switchTaskStatus(taskId);
        String redirectTo = "/tasklist/tasks?tasklistId=" + tasklistId;
        return "redirect:" + redirectTo;
    }
}

package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.entity.TasklistEntity;
import com.veiuper.todolist.model.entity.User;
import com.veiuper.todolist.service.TasklistService;
import com.veiuper.todolist.service.UserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TasklistController {
    TasklistService tasklistService;
    UserService userService;

    @GetMapping("/tasklists")
    public String getAll(Model model, Principal principal) {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        List<TasklistEntity> tasklistEntityList;
        if (optionalUser.isPresent()) {
            tasklistEntityList = tasklistService.getAll(optionalUser.get());
        } else {
            tasklistEntityList = new ArrayList<>();
        }
        model.addAttribute("tasklistList", tasklistEntityList);
        return "tasklists";
    }

    @PostMapping("/add")
    public String addTasklist(
            @ModelAttribute(name = "tasklistEntity") @Valid TasklistEntity tasklistEntity,
            Principal principal
    ) {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        if (optionalUser.isPresent()) {
            tasklistEntity.setUser(optionalUser.get());
            tasklistService.save(tasklistEntity);
        }
        return "redirect:/tasklists";
    }

    @PostMapping("/delete")
    public String deleteTasklist(@RequestParam @Min(0) Long id, Principal principal) {
        TasklistEntity tasklistEntity = tasklistService.getById(id);
        if (!Objects.equals(tasklistEntity.getUser().getEmail(), principal.getName())) {
            throw new AccessDeniedException("Доступ запрещен.");
        }
        tasklistService.delete(id);
        return "redirect:/tasklists";
    }
}

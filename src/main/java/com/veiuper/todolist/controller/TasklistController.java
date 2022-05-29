package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.TasklistEntity;
import com.veiuper.todolist.model.User;
import com.veiuper.todolist.service.TasklistService;
import com.veiuper.todolist.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
    public String addTasklist(@ModelAttribute TasklistEntity tasklistEntity, Principal principal) {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        if (optionalUser.isPresent()) {
            tasklistEntity.setUser(optionalUser.get());
            tasklistService.save(tasklistEntity);
        }
        return "redirect:/tasklists";
    }

    @PostMapping("/delete")
    public String deleteTasklist(@RequestParam Long id) {
        tasklistService.delete(id);
        return "redirect:/tasklists";
    }
}

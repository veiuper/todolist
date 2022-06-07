package com.veiuper.todolist.controller;

import com.veiuper.todolist.exception.BusinessException;
import com.veiuper.todolist.model.entity.User;
import com.veiuper.todolist.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.Optional;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AdminController {
    UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String updateOrDeleteUser(
            @RequestParam @NotBlank String action,
            @RequestParam @Min(0) Long userId,
            Model model,
            Principal principal
    ) throws BusinessException {
        Optional<User> optionalUser = userService.findByEmail(principal.getName());
        if (optionalUser.isPresent() && userId.equals(optionalUser.get().getId())) {
            throw new BusinessException("Пользователю запрещено менять данные своей учетной записи.");
        }
        switch (action) {
            case "delete" -> userService.delete(userId);
            case "switchLocked" -> userService.switchLocked(userId);
            case "switchEnabled" -> userService.switchEnabled(userId);
            default -> throw new BusinessException("Операция " + action + " не поддерживается.");
        }
        return userList(model);
    }

    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") @Min(0) Long userId, Model model) {
        model.addAttribute("allUsers", userService.userGtList(userId));
        return "admin";
    }
}

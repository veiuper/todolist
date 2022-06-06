package com.veiuper.todolist.controller;

import com.veiuper.todolist.dto.Response;
import com.veiuper.todolist.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Controller
@Validated
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
            Model model
    ) {
        if (action.equals("delete")) {
            userService.delete(userId);
        }
        if (action.equals("switchLocked")) {
            userService.switchLocked(userId);
        }
        if (action.equals("switchEnabled")) {
            userService.switchEnabled(userId);
        }
        return userList(model);
    }

    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") @Min(0) Long userId, Model model) {
        model.addAttribute("allUsers", userService.userGtList(userId));
        return "admin";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(
                new Response("Not valid due to validation error: " + e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}

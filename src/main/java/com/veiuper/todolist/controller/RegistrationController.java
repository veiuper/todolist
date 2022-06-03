package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.ConfirmationToken;
import com.veiuper.todolist.model.User;
import com.veiuper.todolist.service.ConfirmationTokenService;
import com.veiuper.todolist.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Controller
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class RegistrationController {
    UserService userService;
    ConfirmationTokenService confirmationTokenService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @ModelAttribute(name = "user") @Valid User userForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают.");
            return "registration";
        }
        if (!userService.registrationUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует.");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/registration/confirm")
    public String confirmMail(@RequestParam("token") @NotBlank String token) {
        Optional<ConfirmationToken> optionalConfirmationToken;
        optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByConfirmationToken(token);
        optionalConfirmationToken.ifPresent(userService::confirmUser);
        return "redirect:/login";
    }
}

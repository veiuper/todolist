package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.User;
import com.veiuper.todolist.service.ConfirmationTokenService;
import com.veiuper.todolist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

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
////        if (!userService.save(userForm)) {
////            model.addAttribute("usernameError", "Пользователь с таким именем уже существует.");
////            return "registration";
////        }
        return "redirect:/";
    }

//    @GetMapping("/confirm")
//    public String confirmMail(@RequestParam("token") String token) {
//        Optional<ConfirmationToken> optionalConfirmationToken;
//        optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByConfirmationToken(token);
//        optionalConfirmationToken.ifPresent(userService::confirmUser);
//        return "registration";
//    }
}

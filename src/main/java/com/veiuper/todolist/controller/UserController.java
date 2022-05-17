package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.User;
import com.veiuper.todolist.service.ConfirmationTokenService;
import com.veiuper.todolist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(User user) {
        userService.signUpUser(user);
        return "redirect:/sign-in";
    }

//    @GetMapping("/confirm")
//    String confirmMail(@RequestParam("token") String token) {
//        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByConfirmationToken(token);
//        optionalConfirmationToken.ifPresent(userService::confirmUser);
//        return "sign-in";
//    }
}

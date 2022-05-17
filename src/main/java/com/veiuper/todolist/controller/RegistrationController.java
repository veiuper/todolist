package com.veiuper.todolist.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class RegistrationController {
//    private final UserService userService;
//    private final ConfirmationTokenService confirmationTokenService;
//
//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("user", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(
//            @ModelAttribute(name = "userForm") @Valid User userForm,
//            BindingResult bindingResult,
//            Model model
//    ) {
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
//            model.addAttribute("passwordError", "Пароли не совпадают.");
//        }
//        if (!userService.save(userForm)) {
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует.");
//            return "registration";
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/confirm")
//    String confirmMail(@RequestParam("token") String token) {
//        Optional<ConfirmationToken> optionalConfirmationToken;
//        optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByConfirmationToken(token);
//        optionalConfirmationToken.ifPresent(userService::confirmUser);
//        return "registration";
//    }
}

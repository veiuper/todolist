package com.veiuper.todolist.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AdminController {
//    private final UserService userService;
//
//    @GetMapping("/admin")
//    public String userList(Model model) {
//        model.addAttribute("allUsers", userService.findAll());
//        return "admin";
//    }
//
//    @PostMapping("/admin/delete/")
//    public String deleteUser(
//            @RequestParam() Long userId,
//            @RequestParam() String action
//    ) {
//        if (action.equals("delete")) {
//            userService.delete(userId);
//        }
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/gt/{userId}")
//    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.userGtList(userId));
//        return "admin";
//    }
}

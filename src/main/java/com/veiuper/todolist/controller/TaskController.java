package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.Task;
import com.veiuper.todolist.model.Tasklist;
import com.veiuper.todolist.service.TaskService;
import com.veiuper.todolist.service.TasklistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final TasklistService tasklistService;

    public TaskController(TaskService taskService, TasklistService tasklistService) {
        this.taskService = taskService;
        this.tasklistService = tasklistService;
    }

    @GetMapping("/tasklist/{id}/tasks")
    public String getAll(Model model, @PathVariable Long id) {
        Tasklist tasklist = tasklistService.getById(id);
        List<Task> taskList = tasklist.getTasks();
        model.addAttribute("tasklist", tasklist);
        model.addAttribute("taskList", taskList);
        model.addAttribute("taskSize", taskList.size());
        return "tasklist";
    }

    @PostMapping("/tasklist/{id}/add")
    public String addTask(@ModelAttribute Task task, @PathVariable Long id) {
        taskService.save(task);
        return "redirect:/tasklist/{id}/tasks";
    }
//    @RequestMapping("/tasklist/{id}/task/delete/{id}")
//    public String deleteTask(@PathVariable Long id) {
//        taskService.delete(id);
//        return "tasklist";
//    }
//
//    @RequestMapping("/tasklist/{id}/tasks/delete")
//    public String deleteAll() {
//        taskService.deleteAll();
//        return "tasklist";
//    }
//
//    @RequestMapping("/tasklist/{id}/task/reversTaskStatus/{id}")
//    public String reversTaskStatus(@PathVariable Long id) throws Exception {
//        taskService.reversTaskStatus(id);
//        return "redirect:/tasklist";
//    }
}

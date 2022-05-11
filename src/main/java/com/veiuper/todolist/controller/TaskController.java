package com.veiuper.todolist.controller;

import com.veiuper.todolist.model.TaskEntity;
import com.veiuper.todolist.model.TasklistEntity;
import com.veiuper.todolist.service.TaskService;
import com.veiuper.todolist.service.TasklistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

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
        TasklistEntity tasklistEntity = tasklistService.getById(id);
        Set<TaskEntity> taskEntityList = tasklistEntity.getTasks();
        model.addAttribute("tasklist", tasklistEntity);
        model.addAttribute("taskList", taskEntityList);
        model.addAttribute("taskSize", taskEntityList.size());
        return "tasklist";
    }

    @PostMapping("/tasklist/{id}/add")
    public String addTask(@ModelAttribute TaskEntity taskEntity, @PathVariable Long id) {
        taskEntity.setTasklist(tasklistService.getById(id));
        taskService.save(taskEntity);
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

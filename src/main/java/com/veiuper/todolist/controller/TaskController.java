package com.veiuper.todolist.controller;

import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
//    private final TaskService taskService;
//    private final TasklistService tasklistService;
//
//    public TaskController(TaskService taskService, TasklistService tasklistService) {
//        this.taskService = taskService;
//        this.tasklistService = tasklistService;
//    }
//
//    @GetMapping("/tasklist/{tasklistId}/tasks")
//    public String getAll(Model model, @PathVariable Long tasklistId) {
//        TasklistEntity tasklistEntity = tasklistService.getById(tasklistId);
//        Set<TaskEntity> taskEntityList = taskService.findByTasklistEntityIdOrderByExecutedAscIdAsc(tasklistId);
//        model.addAttribute("tasklist", tasklistEntity);
//        model.addAttribute("taskList", taskEntityList);
//        return "tasklist";
//    }
//
//    @PostMapping("/tasklist/{tasklistId}/add")
//    public String addTask(@ModelAttribute TaskEntity taskEntity, @PathVariable Long tasklistId) {
//        taskEntity.setTasklist(tasklistService.getById(tasklistId));
//        taskService.save(taskEntity);
//        return "redirect:/tasklist/{tasklistId}/tasks";
//    }
//    @RequestMapping("/tasklist/{tasklistId}/delete/{taskId}")
//    public String deleteTask(@PathVariable String tasklistId, @PathVariable Long taskId) {
//        taskService.delete(taskId);
//        return "redirect:/tasklist/{tasklistId}/tasks";
//    }
//
//    @RequestMapping("/tasklist/{tasklistId}/switchTaskStatus/{taskId}")
//    public String reversTaskStatus(@PathVariable Long tasklistId, @PathVariable Long taskId) throws Exception {
//        taskService.switchTaskStatus(taskId);
//        return "redirect:/tasklist/{tasklistId}/tasks";
//    }
}

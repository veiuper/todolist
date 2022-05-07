package com.veiuper.todolist.service;

import com.veiuper.todolist.dao.TaskRepository;
import com.veiuper.todolist.model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("executed"), Sort.Order.asc("id")));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void reversTaskStatus(long id) throws Exception {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new Exception();
        }
        Task task = optionalTask.get();
        task.setStatus(!task.getExecuted());
        taskRepository.save(task);
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }
}

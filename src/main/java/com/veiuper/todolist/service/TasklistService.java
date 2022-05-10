package com.veiuper.todolist.service;

import com.veiuper.todolist.dao.TasklistRepository;
import com.veiuper.todolist.model.Tasklist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasklistService {
    private final TasklistRepository tasklistRepository;

    public TasklistService(TasklistRepository tasklistRepository) {
        this.tasklistRepository = tasklistRepository;
    }

    public List<Tasklist> getAll() {
        return tasklistRepository.findAll();
    }

    public Tasklist getById(Long id) {
        return tasklistRepository.getById(id);
    }

    public void save(Tasklist tasklist) {
        tasklistRepository.save(tasklist);
    }

    public void delete(Long id) {
        tasklistRepository.deleteById(id);
    }

    public void deleteAll() {
        tasklistRepository.deleteAll();
    }
}

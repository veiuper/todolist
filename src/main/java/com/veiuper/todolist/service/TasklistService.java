package com.veiuper.todolist.service;

import com.veiuper.todolist.dao.TasklistRepository;
import com.veiuper.todolist.model.TasklistEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasklistService {
    private final TasklistRepository tasklistRepository;

    public TasklistService(TasklistRepository tasklistRepository) {
        this.tasklistRepository = tasklistRepository;
    }

    public List<TasklistEntity> getAll() {
        return tasklistRepository.findAll();
    }

    public TasklistEntity getById(Long id) {
        return tasklistRepository.getById(id);
    }

    public void save(TasklistEntity tasklistEntity) {
        tasklistRepository.save(tasklistEntity);
    }

    public void delete(Long id) {
        tasklistRepository.deleteById(id);
    }

//    public void deleteAll() {
//        tasklistRepository.deleteAll();
//    }
}

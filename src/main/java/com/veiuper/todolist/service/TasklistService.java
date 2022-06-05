package com.veiuper.todolist.service;

import com.veiuper.todolist.model.TasklistEntity;
import com.veiuper.todolist.model.User;
import com.veiuper.todolist.repository.TasklistRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TasklistService {
    TasklistRepository tasklistRepository;

    public List<TasklistEntity> getAll(User user) {
        return tasklistRepository.findAllByUser(user);
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
}

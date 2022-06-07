package com.veiuper.todolist.service;

import com.veiuper.todolist.model.entity.TasklistEntity;
import com.veiuper.todolist.model.entity.User;
import com.veiuper.todolist.repository.TasklistRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Validated
public class TasklistService {
    TasklistRepository tasklistRepository;

    public List<TasklistEntity> getAll(@Valid User user) {
        return tasklistRepository.findAllByUser(user);
    }

    public TasklistEntity getById(@Min(0) Long id) {
        return tasklistRepository.getById(id);
    }

    public void save(@Valid TasklistEntity tasklistEntity) {
        tasklistRepository.save(tasklistEntity);
    }

    public void delete(@Min(0) Long id) {
        tasklistRepository.deleteById(id);
    }
}

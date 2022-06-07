package com.veiuper.todolist.service;

import com.veiuper.todolist.exception.BusinessException;
import com.veiuper.todolist.model.entity.TaskEntity;
import com.veiuper.todolist.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Validated
public class TaskService {
    TaskRepository taskRepository;

    public void save(@Valid TaskEntity taskEntity) {
        taskRepository.save(taskEntity);
    }

    public void switchTaskStatus(@Min(0) Long id) throws BusinessException {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new BusinessException(
                    "Не удалось изменить статус выполнения задачи." + System.lineSeparator() +
                    "Задача с id " + id + " не найдена."
            );
        }
        TaskEntity taskEntity = optionalTask.get();
        taskEntity.setExecuted(!taskEntity.getExecuted());
        taskRepository.save(taskEntity);
    }

    public void delete(@Min(0) Long id) {
        taskRepository.deleteById(id);
    }

    public Set<TaskEntity> findByTasklistEntityIdOrderByExecutedAscIdAsc(@Min(0) Long tasklistEntityId) {
        return taskRepository.findByTasklistEntityIdOrderByExecutedAscIdAsc(tasklistEntityId);
    }
}

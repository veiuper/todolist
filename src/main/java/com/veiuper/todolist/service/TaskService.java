package com.veiuper.todolist.service;

import com.veiuper.todolist.dao.TaskRepository;
import com.veiuper.todolist.exception.BusinessException;
import com.veiuper.todolist.model.TaskEntity;
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

    public TaskEntity getById(Long id) {
        return taskRepository.getById(id);
    }

    public List<TaskEntity> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("executed"), Sort.Order.asc("id")));
    }

    public TaskEntity save(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    public void switchTaskStatus(Long id) throws BusinessException {
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

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }
}

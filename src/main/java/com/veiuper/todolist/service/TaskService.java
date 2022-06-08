package com.veiuper.todolist.service;

import com.veiuper.todolist.exception.BusinessException;
import com.veiuper.todolist.model.TaskEntity;
import com.veiuper.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(TaskEntity taskEntity) {
        taskRepository.save(taskEntity);
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

    public Set<TaskEntity> findByTasklistEntityIdOrderByExecutedAscIdAsc(Long tasklistEntityId) {
        return taskRepository.findByTasklistEntityIdOrderByExecutedAscIdAsc(tasklistEntityId);
    }
}

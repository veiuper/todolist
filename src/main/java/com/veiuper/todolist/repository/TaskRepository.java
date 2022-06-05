package com.veiuper.todolist.repository;

import com.veiuper.todolist.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Set<TaskEntity> findByTasklistEntityIdOrderByExecutedAscIdAsc(Long tasklistEntityId);
}

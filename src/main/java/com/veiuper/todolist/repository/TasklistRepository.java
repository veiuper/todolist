package com.veiuper.todolist.repository;

import com.veiuper.todolist.model.TasklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasklistRepository extends JpaRepository<TasklistEntity, Long> {
}

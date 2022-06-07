package com.veiuper.todolist.repository;

import com.veiuper.todolist.model.entity.TasklistEntity;
import com.veiuper.todolist.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasklistRepository extends JpaRepository<TasklistEntity, Long> {
    List<TasklistEntity> findAllByUser(User user);
}

package com.veiuper.todolist.dao;

import com.veiuper.todolist.model.Tasklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasklistRepository extends JpaRepository<Tasklist, Long> {
}

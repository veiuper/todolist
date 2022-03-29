package com.veiuper.todolist.dao;

import com.veiuper.todolist.model.Task;
import com.veiuper.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}

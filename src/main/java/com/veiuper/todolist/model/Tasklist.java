package com.veiuper.todolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tasklist")
public class Tasklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @NotBlank(message="{tasklist.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @OneToMany(mappedBy = "tasklist", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Tasklist() {
    }

    public Tasklist(Long id, @NotBlank(message = "{tasklist.description.invalid}") String description, List<Task> tasks) {
        this.id = id;
        this.description = description;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

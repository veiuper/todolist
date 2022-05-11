package com.veiuper.todolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "tasklist")
public class TasklistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @NotBlank(message="{tasklist.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @OneToMany(mappedBy = "tasklistEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<TaskEntity> taskEntities;

    public TasklistEntity() {
    }

    public TasklistEntity(
            Long id,
            @NotBlank(message = "{tasklist.description.invalid}") String description,
            Set<TaskEntity> taskEntities
    ) {
        this.id = id;
        this.description = description;
        this.taskEntities = taskEntities;
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

    public Set<TaskEntity> getTasks() {
        return taskEntities;
    }

    public void setTasks(Set<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }
}

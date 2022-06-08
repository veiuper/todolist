package com.veiuper.todolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean executed = false;
    @NotBlank(message="{Необходимо заполнить описание задачи}")
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private TasklistEntity tasklistEntity;

    public TaskEntity() {
    }

    public TaskEntity(
            Long id,
            Boolean executed,
            @NotBlank(message = "{task.description.invalid}") String description,
            TasklistEntity tasklistEntity
    ) {
        this.id = id;
        this.executed = executed;
        this.description = description;
        this.tasklistEntity = tasklistEntity;
    }

    public Long getId() {
        return id;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TasklistEntity getTasklist() {
        return tasklistEntity;
    }

    public void setTasklist(TasklistEntity tasklistEntity) {
        this.tasklistEntity = tasklistEntity;
    }
}

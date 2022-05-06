package com.veiuper.todolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean executed = false;
    @NotBlank(message="{task.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @ManyToOne()
    private Tasklist tasklist;

    public Task() {
    }

    public Task(Long id, Tasklist tasklist, Boolean executed, @NotBlank(message = "{task.description.invalid}") String description) {
        this.id = id;
        this.tasklist = tasklist;
        this.executed = executed;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setStatus(Boolean executed) {
        this.executed = executed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tasklist getTasklist() {
        return tasklist;
    }

    public void setTasklist(Tasklist tasklist) {
        this.tasklist = tasklist;
    }
}

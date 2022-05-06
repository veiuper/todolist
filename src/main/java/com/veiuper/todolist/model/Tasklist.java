package com.veiuper.todolist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Tasklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @NotBlank(message="{tasklist.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    private String description;

    public Tasklist() {
    }

    public Tasklist(Long id, @NotBlank(message = "{tasklist.description.invalid}") String description) {
        this.id = id;
        this.description = description;
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
}

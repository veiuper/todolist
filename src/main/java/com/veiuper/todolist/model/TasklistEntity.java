package com.veiuper.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "tasklist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TasklistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @NotBlank(message="{tasklist.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @OneToMany(mappedBy = "tasklistEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<TaskEntity> taskEntities;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}

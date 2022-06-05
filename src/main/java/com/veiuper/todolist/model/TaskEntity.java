package com.veiuper.todolist.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    Long id;
    @Column(nullable = false, columnDefinition = "boolean default false")
    Boolean executed = false;
    @NotBlank(message="{task.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    TasklistEntity tasklistEntity;
}

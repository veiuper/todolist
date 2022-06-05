package com.veiuper.todolist.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "tasklist")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TasklistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    Long id;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    User user;
    @OneToMany(mappedBy = "tasklistEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Set<TaskEntity> taskEntities;
}

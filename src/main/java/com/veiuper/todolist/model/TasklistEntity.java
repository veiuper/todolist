package com.veiuper.todolist.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message="{tasklist.description.invalid}")
    @Column(columnDefinition = "varchar(255)")
    String description;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;
    @OneToMany(mappedBy = "tasklistEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Set<TaskEntity> taskEntities;
}

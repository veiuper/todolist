package com.veiuper.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");
    private final String code;
}

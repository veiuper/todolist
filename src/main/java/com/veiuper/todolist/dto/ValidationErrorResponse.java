package com.veiuper.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ValidationErrorResponse {
    private final List<Violation> violations;
}

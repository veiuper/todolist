package com.veiuper.todolist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String debugMessage;

    public Response(String message) {
        this.message = message;
    }
}

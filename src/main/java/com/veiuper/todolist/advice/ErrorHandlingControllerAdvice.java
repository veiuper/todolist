package com.veiuper.todolist.advice;

import com.veiuper.todolist.dto.ValidationErrorResponse;
import com.veiuper.todolist.dto.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@ControllerAdvice
class ErrorHandlingControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse(new ArrayList<>());
        e.getConstraintViolations().forEach(violation -> error.getViolations().add(
                new Violation(violation.getPropertyPath().toString(), violation.getMessage())
        ));
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse(new ArrayList<>());
        e.getBindingResult().getFieldErrors().forEach(fieldError -> error.getViolations().add(
                new Violation(fieldError.getField(), fieldError.getDefaultMessage())
        ));
        return error;
    }
}
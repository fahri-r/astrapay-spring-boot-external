package com.astrapay.controller.advice;

import com.astrapay.dto.BaseApiDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ConstraintExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseApiDto<Void> constraintViolationHandler(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(error -> {
                    var propertyName = error.getPropertyPath().toString();
                    var message = error.getMessage();

                    return propertyName + ": " + message;
                }).collect(Collectors.toList());

        return new BaseApiDto<>("FAILED", errors);
    }
}

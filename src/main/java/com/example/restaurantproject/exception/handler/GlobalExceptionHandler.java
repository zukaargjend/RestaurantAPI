package com.example.restaurantproject.exception.handler;

import com.example.restaurantproject.dto.exception.ExceptionResponseDto;
import com.example.restaurantproject.exception.EntityAlreadyExistsException;
import com.example.restaurantproject.exception.EntityNotFoundException;
import com.example.restaurantproject.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request){

        Map<String, String> fieldAndExceptionDetails = new HashMap<>();
        for(FieldError error : exception.getBindingResult().getFieldErrors()){
            fieldAndExceptionDetails.put(error.getField(), error.getDefaultMessage());
        }

        String field = fieldAndExceptionDetails.keySet().iterator().next();
        String message = fieldAndExceptionDetails.get(field);

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(400, field, message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected ResponseEntity<ExceptionResponseDto> handleEntityAlreadyExistsException(EntityAlreadyExistsException exception){
        log.error("Duplicate entity error: {}", exception.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(400, null, exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ExceptionResponseDto> handleEntityNotFoundException(EntityNotFoundException exception){
        log.error("Entity not found error: {}", exception.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(404, null, exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ExceptionResponseDto> handleValidationException(ValidationException exception){
        log.error("Validation error: {}", exception.getMessage());

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(400, null, exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

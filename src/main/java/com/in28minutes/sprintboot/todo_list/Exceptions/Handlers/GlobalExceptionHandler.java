package com.in28minutes.sprintboot.todo_list.Exceptions.Handlers;

import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.DeletingDefaultRolesException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceAlreadyPresentException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.ResourceNotFoundException;
import com.in28minutes.sprintboot.todo_list.Exceptions.Exception.UnauthorisedActionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class , HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(Exception ex){
        if (ex instanceof MethodArgumentNotValidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ExceptionResponse.builder()
                            .localErrorDescription("Validation Error")
                            .validationErrors(e.getBindingResult().getFieldErrors().stream()
                                    .map(error -> error.getField() + " : " + error.getDefaultMessage())
                                    .collect(Collectors.toSet()))
                            .build()
                    );
        } else if (ex instanceof HttpMessageNotReadableException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ExceptionResponse.builder()
                            .localErrorDescription("Validation Error")
                            .error("date should be in the format yyyy-MM-dd")
                            .build());

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .localErrorDescription("Validation Error")
                        .error(ex.getMessage())
                        .build()
                );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .localErrorDescription("Internal Server Error. Please try again later.")
                        .error(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder()
                        .localErrorDescription("Resource not found!")
                        .error(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(ResourceAlreadyPresentException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyPresentException(ResourceAlreadyPresentException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
                        .localErrorDescription("Resource already present!")
                        .error(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(DeletingDefaultRolesException.class)
    public ResponseEntity<ExceptionResponse> handleDeletingDefaultRolesException(DeletingDefaultRolesException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .localErrorDescription("Cannot delete default roles!")
                        .error(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(UnauthorisedActionException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorisedActionException(UnauthorisedActionException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .localErrorDescription("Unauthorised Action!")
                        .error(e.getMessage())
                        .build()
                );
    }
}

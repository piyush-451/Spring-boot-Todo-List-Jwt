package com.in28minutes.sprintboot.todo_list.Exceptions.Handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private Integer localErrorCode;
    private String localErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;

}

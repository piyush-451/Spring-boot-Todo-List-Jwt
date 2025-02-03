package com.in28minutes.sprintboot.todo_list.DataModels.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationRequest {
    @NotBlank(message = "Username is required")
    @NotNull(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,}$", message = "Username must be at least 5 characters long and contain only letters and numbers")
    private String username;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\W]{5,}$", message = "Password must be at least 5 characters long and can contain letters, numbers, and special symbols")
    private String password;
}

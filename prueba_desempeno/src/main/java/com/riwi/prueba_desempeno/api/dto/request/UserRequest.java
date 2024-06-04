package com.riwi.prueba_desempeno.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
 
    @NotBlank(message = "name is required")
    @Size(min = 1, max = 100, message = "the name must contain between 1 and 100 characters")
    private String name;

    @NotBlank(message = "password is required")
    @Size(min = 1, max = 10, message = "the name must contain between 1 and 10 characters")
    private String password;

    @NotBlank(message = "email is required")
    @Size(min = 1, max = 100, message = "the email must contain between 1 and 10 characters")
    @Email(message = "Email not valid")
    private String email;

    @NotNull(message = "status is required")
    private boolean active;
}

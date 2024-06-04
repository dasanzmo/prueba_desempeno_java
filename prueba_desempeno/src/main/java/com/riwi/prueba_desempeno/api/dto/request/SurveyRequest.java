package com.riwi.prueba_desempeno.api.dto.request;

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
public class SurveyRequest {

     
    @NotBlank(message = "title is required")
    @Size(min = 1, max = 100, message = "the title must contain between 1 and 100 characters")
    private String title;

    @NotBlank(message = "name is required")
    @Size(min = 1, max = 100, message = "the description must contain between 1 and 100 characters")
    private String description;

    @NotNull(message = "status is required")
    private boolean active;

    @NotNull(message = "Id creator is required")
    private Long creator;
    
}

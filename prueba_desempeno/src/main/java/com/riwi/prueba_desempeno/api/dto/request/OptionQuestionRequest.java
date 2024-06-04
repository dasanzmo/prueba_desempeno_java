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
public class OptionQuestionRequest {

    @NotBlank(message = "text is required")
    @Size(min = 1, max = 100, message = "the text must contain between 1 and 100 characters")
    private String text;

    @NotNull(message = "status is required")
    private boolean active;

    @NotNull(message = "Id question is required")
    private Long question;

    
}

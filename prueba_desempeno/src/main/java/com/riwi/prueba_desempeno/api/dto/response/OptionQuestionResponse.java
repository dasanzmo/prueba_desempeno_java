package com.riwi.prueba_desempeno.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionQuestionResponse {
    private Long id;
    private String text;
    private boolean active;
    private QuestionResponse question;
}

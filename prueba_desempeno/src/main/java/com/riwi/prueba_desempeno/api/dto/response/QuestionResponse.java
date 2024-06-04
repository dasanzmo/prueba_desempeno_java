package com.riwi.prueba_desempeno.api.dto.response;

import com.riwi.prueba_desempeno.util.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String text;
    private QuestionType type;
    private boolean active;
    private SurveyResponse survey;
}

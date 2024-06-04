package com.riwi.prueba_desempeno.api.dto.response;

import java.util.List;

import com.riwi.prueba_desempeno.util.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseOptions {
    private Long id;
    private String text;
    private QuestionType type;
    private boolean active;
    private SurveyResponse survey;
    private List<OptionQuestionResponse> options;
    
}

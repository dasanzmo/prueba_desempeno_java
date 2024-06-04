package com.riwi.prueba_desempeno.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseQuestions {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean active;
    private UserReponse creator;
    private List<QuestionResponse> questions;
}

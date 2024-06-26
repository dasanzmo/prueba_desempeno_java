package com.riwi.prueba_desempeno.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseSurveys {
    private Long id;
    private String name;
    private String email;
    private boolean active;
    private List<SurveyResponse> surveys;
}

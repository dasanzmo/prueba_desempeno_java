package com.riwi.prueba_desempeno.infraestructure.helpers;

import com.riwi.prueba_desempeno.api.dto.request.SurveyRequest;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponse;
import com.riwi.prueba_desempeno.domain.entities.Survey;

public class SurveyHelper {

    public static SurveyResponse SurveyToResp(Survey survey) {
        return SurveyResponse.builder()
                .id(survey.getId())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .creationDate(survey.getCreationDate())
                .active(survey.isActive())
                .creator(UserHelper.userToResp(survey.getCreator()))
                .build();
    }

    public static Survey reqToSurvey(SurveyRequest surveyReq) {

        return Survey.builder()
                .title(surveyReq.getTitle())
                .description(surveyReq.getDescription())
                .active(surveyReq.isActive())
                .build();

    }

}

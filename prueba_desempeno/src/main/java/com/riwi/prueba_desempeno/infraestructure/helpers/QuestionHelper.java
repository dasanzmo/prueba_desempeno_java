package com.riwi.prueba_desempeno.infraestructure.helpers;

import com.riwi.prueba_desempeno.api.dto.request.QuestionRequest;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponse;
import com.riwi.prueba_desempeno.domain.entities.Question;

public class QuestionHelper {

    public static QuestionResponse questionToResp(Question question) {
        return QuestionResponse.builder()
        .id(question.getId())
        .text(question.getText())
        .type(question.getType())
        .active(question.isActive())
        .survey(SurveyHelper.SurveyToResp(question.getSurvey()))
        .build();
    }

    public static Question reqToQuestion(QuestionRequest questionReq) {
        return Question.builder()
                .text(questionReq.getText())
                .type(questionReq.getType())
                .active(questionReq.isActive())
                .build();
    }

}

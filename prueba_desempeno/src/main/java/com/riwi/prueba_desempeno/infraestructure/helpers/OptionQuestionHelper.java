package com.riwi.prueba_desempeno.infraestructure.helpers;

import com.riwi.prueba_desempeno.api.dto.request.OptionQuestionRequest;
import com.riwi.prueba_desempeno.api.dto.response.OptionQuestionResponse;
import com.riwi.prueba_desempeno.domain.entities.OptionQuestion;

public class OptionQuestionHelper {

    public static OptionQuestionResponse optionQuestionToResp(OptionQuestion optionQuestion) {
        return OptionQuestionResponse.builder()
        .id(optionQuestion.getId())
        .text(optionQuestion.getText())
        .active(optionQuestion.isActive())
        .question(QuestionHelper.questionToResp(optionQuestion.getQuestion()))
        .build();
    }

    public static OptionQuestion reqToOptionQuestion(OptionQuestionRequest optionQuestionReq) {
        return OptionQuestion.builder()
                .text(optionQuestionReq.getText())
                .active(optionQuestionReq.isActive())
                .build();
    }

}

package com.riwi.prueba_desempeno.infraestructure.abstract_services;

import com.riwi.prueba_desempeno.api.dto.request.QuestionRequest;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponse;


public interface IOptionQuestionService extends CrudAbstractService<QuestionRequest, QuestionResponse, Long>{
    public String FIELD_BY_SORT = "text";
}

package com.riwi.prueba_desempeno.infraestructure.abstract_services;

import com.riwi.prueba_desempeno.api.dto.request.QuestionRequest;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponse;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponseOptions;


public interface IQuestionService extends CrudAbstractService<QuestionRequest, QuestionResponse, Long>{
    public String FIELD_BY_SORT = "text";
    public QuestionResponseOptions getQuestionOptions(Long id);
}

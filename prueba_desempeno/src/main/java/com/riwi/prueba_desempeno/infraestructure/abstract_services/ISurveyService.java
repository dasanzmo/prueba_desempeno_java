package com.riwi.prueba_desempeno.infraestructure.abstract_services;

import com.riwi.prueba_desempeno.api.dto.request.SurveyRequest;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponse;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponseQuestions;

public interface ISurveyService extends CrudAbstractService<SurveyRequest, SurveyResponse, Long>{
    public String FIELD_BY_SORT = "title";
    public SurveyResponseQuestions getSurveysQuestions(Long id);
    
}

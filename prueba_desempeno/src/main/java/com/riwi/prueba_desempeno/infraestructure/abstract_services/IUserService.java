package com.riwi.prueba_desempeno.infraestructure.abstract_services;

import com.riwi.prueba_desempeno.api.dto.request.UserRequest;
import com.riwi.prueba_desempeno.api.dto.response.UserResponse;
import com.riwi.prueba_desempeno.api.dto.response.UserResponseSurveys;

public interface IUserService extends CrudAbstractService<UserRequest, UserResponse, String>{
    public String FIELD_BY_SORT = "name";
    public UserResponseSurveys getUsersSurveys(Long id);
}

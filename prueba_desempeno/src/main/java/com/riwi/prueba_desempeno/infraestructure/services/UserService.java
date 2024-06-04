package com.riwi.prueba_desempeno.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.prueba_desempeno.api.dto.request.UserRequest;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponse;
import com.riwi.prueba_desempeno.api.dto.response.UserReponse;
import com.riwi.prueba_desempeno.api.dto.response.UserResponseSurveys;
import com.riwi.prueba_desempeno.domain.entities.User;
import com.riwi.prueba_desempeno.domain.repositories.SurveyRepository;
import com.riwi.prueba_desempeno.domain.repositories.UserRepository;
import com.riwi.prueba_desempeno.infraestructure.abstract_services.IUserService;
import com.riwi.prueba_desempeno.infraestructure.helpers.SurveyHelper;
import com.riwi.prueba_desempeno.infraestructure.helpers.UserHelper;
import com.riwi.prueba_desempeno.util.enums.SortType;
import com.riwi.prueba_desempeno.util.exceptions.BadRequestException;
import com.riwi.prueba_desempeno.util.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SurveyRepository surveyRepository;

    /* OBTENER TODOS LOS USUARIOS */
    @Override
    public Page<UserReponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pagination)
                .map(user -> UserHelper.userToResp(user));
    }

    /* CREAR USUARIO */
    @Override
    public UserReponse create(UserRequest request) {
        return UserHelper.userToResp(userRepository.save(UserHelper.reqToUser(request)));
    }

    /* ACTUALIZAR TODOS LOS USUARIOS */
    @Override
    public UserReponse update(UserRequest request, Long id) {
        find(id);
        User user = UserHelper.reqToUser(request);
        user.setId(id);
        return UserHelper.userToResp(userRepository.save(user));
    }

    /* OBTENER USUARIO POR ID */
    @Override
    public UserReponse getById(Long id) {
        User user = find(id);
        return UserHelper.userToResp(user);
    }

    /* ELIMINAR USUARIO */
    @Override
    public void delete(Long id) {
        userRepository.delete(find(id));
    }

    @Override
    public UserResponseSurveys getUsersSurveys(Long id) {

        List<SurveyResponse> surveys = surveyRepository.findByCreatorId(id).stream()
                .map(survey -> SurveyHelper.SurveyToResp(survey)).toList();

        User user = find(id);
        return UserResponseSurveys.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .surveys(surveys)
                .build();
    }

    // ***************** UTILS *********************//
    private User find(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));
    }
}

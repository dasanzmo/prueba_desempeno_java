package com.riwi.prueba_desempeno.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.prueba_desempeno.api.dto.request.SurveyRequest;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponse;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponse;
import com.riwi.prueba_desempeno.api.dto.response.SurveyResponseQuestions;
import com.riwi.prueba_desempeno.domain.entities.Survey;
import com.riwi.prueba_desempeno.domain.entities.User;
import com.riwi.prueba_desempeno.domain.repositories.QuestionRepository;
import com.riwi.prueba_desempeno.domain.repositories.SurveyRepository;
import com.riwi.prueba_desempeno.domain.repositories.UserRepository;
import com.riwi.prueba_desempeno.infraestructure.abstract_services.ISurveyService;
import com.riwi.prueba_desempeno.infraestructure.helpers.QuestionHelper;
import com.riwi.prueba_desempeno.infraestructure.helpers.SurveyHelper;
import com.riwi.prueba_desempeno.infraestructure.helpers.UserHelper;
import com.riwi.prueba_desempeno.util.enums.SortType;
import com.riwi.prueba_desempeno.util.exceptions.BadRequestException;
import com.riwi.prueba_desempeno.util.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService implements ISurveyService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    /* OBTENER TODAS LAS ENCUESTAS */
    @Override
    public Page<SurveyResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.surveyRepository.findAll(pagination)
                .map(survey -> SurveyHelper.SurveyToResp(survey));
    }

    /* CREAR ENCUESTA */
    @Override
    public SurveyResponse create(SurveyRequest request) {

        User creator = userRepository.findById(request.getCreator())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));

        Survey survey = SurveyHelper.reqToSurvey(request);

        survey.setCreator(creator);

        return SurveyHelper.SurveyToResp(surveyRepository.save(survey));
    }

    /* ACTUALIZAR UNA ENCUESTA */
    @Override
    public SurveyResponse update(SurveyRequest request, Long id) {
        Survey surveyFound = find(id);
        Survey survey = SurveyHelper.reqToSurvey(request);
        survey.setId(id);
        survey.setCreator(surveyFound.getCreator());
        return SurveyHelper.SurveyToResp(surveyRepository.save(survey));
    }

    /* OBTENER ENCUESTAS POR ID */
    @Override
    public SurveyResponse getById(Long id) {
        Survey survey = find(id);
        return SurveyHelper.SurveyToResp(survey);
    }

    /* ELIMINAR ENCUESTA */
    @Override
    public void delete(Long id) {
        surveyRepository.delete(find(id));
    }

    @Override
    public SurveyResponseQuestions getSurveysQuestions(Long id) {
        List<QuestionResponse> questions = questionRepository.findBySurveyId(id).stream()
                .map(question -> QuestionHelper.questionToResp(question)).toList();

        Survey survey = find(id);
        return SurveyResponseQuestions.builder()
                .id(survey.getId())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .creationDate(survey.getCreationDate())
                .active(survey.isActive())
                .creator(UserHelper.userToResp(survey.getCreator()))
                .questions(questions)
                .build();
    }

    // ***************** UTILS *********************//
    private Survey find(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("survey")));
    }


}

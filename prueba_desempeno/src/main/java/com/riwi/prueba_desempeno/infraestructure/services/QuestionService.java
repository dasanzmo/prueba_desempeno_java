package com.riwi.prueba_desempeno.infraestructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.prueba_desempeno.api.dto.request.QuestionEdited;
import com.riwi.prueba_desempeno.api.dto.request.QuestionRequest;
import com.riwi.prueba_desempeno.api.dto.response.OptionQuestionResponse;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponse;
import com.riwi.prueba_desempeno.api.dto.response.QuestionResponseOptions;
import com.riwi.prueba_desempeno.domain.entities.Question;
import com.riwi.prueba_desempeno.domain.entities.Survey;
import com.riwi.prueba_desempeno.domain.repositories.OptionQuestionRepository;
import com.riwi.prueba_desempeno.domain.repositories.QuestionRepository;
import com.riwi.prueba_desempeno.domain.repositories.SurveyRepository;
import com.riwi.prueba_desempeno.infraestructure.abstract_services.IQuestionService;
import com.riwi.prueba_desempeno.infraestructure.helpers.OptionQuestionHelper;
import com.riwi.prueba_desempeno.infraestructure.helpers.QuestionHelper;
import com.riwi.prueba_desempeno.infraestructure.helpers.SurveyHelper;
import com.riwi.prueba_desempeno.util.enums.SortType;
import com.riwi.prueba_desempeno.util.exceptions.BadRequestException;
import com.riwi.prueba_desempeno.util.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {

    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final OptionQuestionRepository optionQuestionRepository;

    /* OBTENER TODAS LAS ENCUESTAS */
    @Override
    public Page<QuestionResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.questionRepository.findAll(pagination)
                .map(question-> QuestionHelper.questionToResp(question));
    }

    /* CREAR PREGUNTA */
    @Override
    public QuestionResponse create(QuestionRequest request) {

        Survey survey = surveyRepository.findById(request.getSurvey())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("survey")));

        Question question = QuestionHelper.reqToQuestion(request);

        question.setSurvey(survey);

        return QuestionHelper.questionToResp(questionRepository.save(question));
    }

    /* ACTUALIZAR UNA PREGUNTA*/
    @Override
    public QuestionResponse update(QuestionRequest request, Long id) {
        Question questionFound = find(id);
        Question question = QuestionHelper.reqToQuestion(request);
        question.setId(id);
        question.setSurvey(questionFound.getSurvey());
        return QuestionHelper.questionToResp(questionRepository.save(question));
    }

    /* OBTENER PREGUNTAS POR ID */
    @Override
    public QuestionResponse getById(Long id) {
        Question question = find(id);
        return QuestionHelper.questionToResp(question);
    }

    /* ELIMINAR PREGUNTA */
    @Override
    public void delete(Long id) {
        questionRepository.delete(find(id));
    }

    @Override
    public QuestionResponseOptions getQuestionOptions(Long id) {

        List<OptionQuestionResponse> options = optionQuestionRepository.findByQuestionId(id).stream()
            .map(option -> OptionQuestionHelper.optionQuestionToResp(option)).toList();

        Question question = find(id);
        return QuestionResponseOptions.builder()
                .id(question.getId())
                .text(question.getText())
                .active(question.isActive())
                .options(options)
                .survey(SurveyHelper.SurveyToResp(question.getSurvey()))
                .build();

    }

    // ***************** UTILS *********************//
    private Question find(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("question")));
    }

    public QuestionResponse updateQuestionText(QuestionEdited QuestionEdited, Long id) {
        Question question = find(id);
        question.setText(QuestionEdited.getText());
        return QuestionHelper.questionToResp(question);
    }

}

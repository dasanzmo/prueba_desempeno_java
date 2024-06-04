package com.riwi.prueba_desempeno.domain.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.prueba_desempeno.domain.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
    public List<Question> findBySurveyId(Long id);
}

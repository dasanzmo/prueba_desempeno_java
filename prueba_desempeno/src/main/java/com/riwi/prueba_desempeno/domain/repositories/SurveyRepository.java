package com.riwi.prueba_desempeno.domain.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.prueba_desempeno.domain.entities.Survey;

@Repository
public interface  SurveyRepository extends JpaRepository<Survey, Long>{
    public List<Survey> findByCreatorId(Long id);
}

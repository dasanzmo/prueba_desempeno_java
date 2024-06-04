package com.riwi.prueba_desempeno.domain.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.prueba_desempeno.domain.entities.OptionQuestion;

@Repository
public interface OptionQuestionRepository extends JpaRepository<OptionQuestion, Long>{
    public List<OptionQuestion> findByQuestionId(Long id);
}

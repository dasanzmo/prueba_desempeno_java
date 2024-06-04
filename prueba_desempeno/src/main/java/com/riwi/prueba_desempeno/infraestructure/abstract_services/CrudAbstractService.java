package com.riwi.prueba_desempeno.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.prueba_desempeno.util.enums.SortType;

public interface CrudAbstractService<RQ, RS, ID> {
    RS create(RQ request);

    RS update(RQ request, ID id);
    
    RS getById(ID id);

    void delete(ID id);

    Page<RS> getAll(int page, int size, SortType sortType);
}

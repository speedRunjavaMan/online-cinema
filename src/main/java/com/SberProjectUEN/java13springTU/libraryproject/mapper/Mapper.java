package com.SberProjectUEN.java13springTU.libraryproject.mapper;

import com.SberProjectUEN.java13springTU.libraryproject.dto.GenericDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);
    
    List<E> toEntities(List<D> dtos);
    
    D toDto(E entity);
    
    List<D> toDTOs(List<E> entities);
}

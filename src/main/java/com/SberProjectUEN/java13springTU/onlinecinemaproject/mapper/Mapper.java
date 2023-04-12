package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;


import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.GenericDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);

    List<E> toEntities(List<D> dtos);

    D toDTO(E entity);

    List<D> toDTOs(List<E> entities);
}


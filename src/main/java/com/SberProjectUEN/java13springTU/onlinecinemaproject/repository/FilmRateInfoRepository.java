package com.SberProjectUEN.java13springTU.onlinecinemaproject.repository;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.FilmRateInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRateInfoRepository
        extends GenericRepository<FilmRateInfo> {
    Page<FilmRateInfo> getFilmRateInfoByUserId(Long userId,
                                               Pageable pageable);
}


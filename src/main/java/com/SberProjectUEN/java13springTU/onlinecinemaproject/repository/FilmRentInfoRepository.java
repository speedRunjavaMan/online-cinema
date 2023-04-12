package com.SberProjectUEN.java13springTU.onlinecinemaproject.repository;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.FilmRentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRentInfoRepository
      extends GenericRepository<FilmRentInfo> {
    Page<FilmRentInfo> getFilmRentInfoByUserId(Long userId,
                                               Pageable pageable);
}

package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.FilmRentInfoMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.FilmRentInfo;
import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRentInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FilmRentInfoService
        extends GenericService<FilmRentInfo, FilmRentInfoDTO> {
    private FilmRentInfoRepository filmRentInfoRepository;

    protected FilmRentInfoService(FilmRentInfoRepository filmRentInfoRepository,
                                  FilmRentInfoMapper filmRentInfoMapper) {
        super(filmRentInfoRepository, filmRentInfoMapper);
        this.filmRentInfoRepository = filmRentInfoRepository;
    }

    public FilmRentInfoDTO makeOrderRentOrBuy(Film film, User user, LocalDateTime rentDate, Integer rentPeriod, Boolean purchase) {
        FilmRentInfo object = new FilmRentInfo(film, user, rentDate, rentPeriod, purchase);
//        object.getFilmId();
//        Set<Long> dir = (filmId.getDirectorsIds());
//        dir.add(directorDTO.getId());
//        filmId.setDirectorsIds(dir);
        return mapper.toDto(repository.save(object));
    }
}


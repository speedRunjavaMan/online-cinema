package com.SberProjectUEN.java13springTU;

import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.FilmRentInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface FilmRentInfoData {

    FilmRentInfoDTO FILM_RENT_INFO_DTO = new FilmRentInfoDTO(LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            14,
            1L,
            1L,
            null,
            false);

    List<FilmRentInfoDTO> FILM_RENT_INFO_DTO_LIST = List.of(FILM_RENT_INFO_DTO);

    FilmRentInfo FILM_RENT_INFO = new FilmRentInfo(null,
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            false,
            14);

    List<FilmRentInfo> FILM_RENT_INFO_LIST = List.of(FILM_RENT_INFO);
}
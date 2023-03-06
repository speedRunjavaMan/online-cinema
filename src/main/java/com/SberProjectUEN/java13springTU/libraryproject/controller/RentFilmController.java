package com.SberProjectUEN.java13springTU.libraryproject.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.FilmRentInfo;
import com.SberProjectUEN.java13springTU.libraryproject.service.FilmRentInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Аренда фильмов",
     description = "Контроллер для работы с арендой/покупкой фильмов пользователями онлайн кинотеатра")
public class RentFilmController
        extends GenericController<FilmRentInfo, FilmRentInfoDTO> {
    public RentFilmController(FilmRentInfoService filmRentInfoService) {
        super(filmRentInfoService);
    }
}


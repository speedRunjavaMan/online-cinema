package com.SberProjectUEN.java13springTU.libraryproject.controller;

import com.SberProjectUEN.java13springTU.libraryproject.model.FilmRentInfo;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRentInfoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Аренда фильмов",
     description = "Контроллер для работы с арендой/выдачей фильмов пользователям онлайн кинотеатра")
public class RentFilmController
      extends GenericController<FilmRentInfo> {
    
    private final FilmRentInfoRepository repository;
    
    public RentFilmController(FilmRentInfoRepository repository) {
        super(repository);
        this.repository = repository;
    }
}

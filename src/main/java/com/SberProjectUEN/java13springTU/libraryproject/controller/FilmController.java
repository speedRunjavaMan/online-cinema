package com.SberProjectUEN.java13springTU.libraryproject.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы",
     description = "Контроллер для работы с фильмами")
//localhost:9090/api/rest/films
public class FilmController
      extends GenericController<Film, FilmDTO> {

    public FilmController(FilmService filmService) {
        super(filmService);
    }

    
//    @Operation(description = "Добавить режиссера к фильму", method = "addDirector")
//    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Film> addDirector(@RequestParam(value = "filmId") Long filmId,
//                                          @RequestParam(value = "directorId") Long directorId) {
////        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм с переданным ID не найден"));
////        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Режиссер с таким ID не найден"));
////        film.getDirectors().add(director);
////        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(film));
//    }





}

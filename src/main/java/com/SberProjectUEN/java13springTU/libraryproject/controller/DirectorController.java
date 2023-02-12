package com.SberProjectUEN.java13springTU.libraryproject.controller;

import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.repository.DirectorRepository;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


@RestController
@RequestMapping("/director")
@Tag(name = "Режиссеры",
     description = "Контроллер для работы с режиссерами фильмов")
public class DirectorController
      extends GenericController<Director> {
    
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    
    public DirectorController(FilmRepository filmRepository,
                            DirectorRepository directorRepository) {
        super(directorRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }
    
    @Operation(description = "Добавить фильм к режиссеру", method = "addFilm")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Director> addDirector(@RequestParam(value = "filmId") Long filmId,
                                            @RequestParam(value = "directorId") Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм с переданным ID не найден"));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Режиссер с таким ID не найден"));
        director.getFilms().add(film);
        return ResponseEntity.status(HttpStatus.OK).body(directorRepository.save(director));
    }
}

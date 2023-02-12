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
@RequestMapping("/films")
@Tag(name = "Фильмы",
     description = "Контроллер для работы с фильмами")
//localhost:9090/api/rest/films
public class FilmController
      extends GenericController<Film> {
    
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    
    public FilmController(FilmRepository filmRepository,
                          DirectorRepository directorRepository) {
        super(filmRepository);
        this.filmRepository = filmRepository;
        this.directorRepository = directorRepository;
    }
    
    @Operation(description = "Добавить режиссера к фильму", method = "addDirector")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Film> addDirector(@RequestParam(value = "filmId") Long filmId,
                                          @RequestParam(value = "directorId") Long directorId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Книга с переданным ID не найдена"));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Автор с таким ID не найден"));
        film.getDirectors().add(director);
        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(film));
    }
}

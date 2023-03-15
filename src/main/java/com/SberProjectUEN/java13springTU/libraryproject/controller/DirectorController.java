package com.SberProjectUEN.java13springTU.libraryproject.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.service.DirectorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/director")
@Tag(name = "Режиссеры",
     description = "Контроллер для работы с режиссерами фильмов")
public class DirectorController
      extends GenericController<Director, DirectorDTO> {


    public DirectorController(DirectorService directorService) {
            super(directorService);
        }

    
//    @Operation(description = "Добавить фильм к режиссеру", method = "addFilm")
//    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Director> addDirector(@RequestParam(value = "filmId") Long filmId,
//                                            @RequestParam(value = "directorId") Long directorId) {
//        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм с переданным ID не найден"));
//        Director director = directorRepository.findById(directorId).orElseThrow(() -> new NotFoundException("Режиссер с таким ID не найден"));
//        director.getFilms().add(film);
//        return ResponseEntity.status(HttpStatus.OK).body(directorRepository.save(director));
//    }
}

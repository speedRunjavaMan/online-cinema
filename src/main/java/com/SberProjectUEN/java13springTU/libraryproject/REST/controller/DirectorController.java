package com.SberProjectUEN.java13springTU.libraryproject.REST.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.service.DirectorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/director")
@Tag(name = "Режиссеры",
     description = "Контроллер для работы с режиссерами фильмов")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DirectorController
      extends GenericController<Director, DirectorDTO> {

    private DirectorService directorService;


    public DirectorController(DirectorService directorService) {
        super(directorService);
        this.directorService = directorService;
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

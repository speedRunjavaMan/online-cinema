package com.SberProjectUEN.java13springTU.onlinecinemaproject.REST.controller;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.ComposerDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Composer;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.ComposerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/composer")
@Tag(name = "Композиторы",
        description = "Контроллер для работы с композиторами фильмов")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComposerController
        extends GenericController<Composer, ComposerDTO> {

    private ComposerService composerService;


    public ComposerController(ComposerService composerService) {
        super(composerService);
        this.composerService = composerService;
    }

//    @Operation(description = "Добавить фильм к композитору", method = "addFilm")
//    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Composer> addComposer(@RequestParam(value = "filmId") Long filmId,
//                                            @RequestParam(value = "composerId") Long composerId) {
//        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Фильм с переданным ID не найден"));
//        Composer composer = composerRepository.findById(composerId).orElseThrow(() -> new NotFoundException("Композитор с таким ID не найден"));
//        composer.getFilms().add(film);
//        return ResponseEntity.status(HttpStatus.OK).body(composerRepository.save(composer));
//    }
}



package com.SberProjectUEN.java13springTU.onlinecinemaproject.MVC.controller;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.*;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.ComposerService;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.DirectorService;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.FilmService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.SberProjectUEN.java13springTU.onlinecinemaproject.constants.UserRolesConstants.ADMIN;

@Hidden
@Controller
@RequestMapping("films")
@Slf4j
public class MVCFilmController {
    private final DirectorService directorService;
    private final FilmService filmService;
    private final ComposerService composerService;
    public MVCFilmController(DirectorService directorService,
                             FilmService filmService,
                             ComposerService composerService) {
        this.directorService = directorService;
        this.filmService = filmService;
        this.composerService = composerService;
    }

    //url: localhost:9090/api/films/?page=1&size=2
    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "filmTitle"));
        Page<FilmWithDirectorsDTO> result;
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ADMIN.equalsIgnoreCase(userName)) {
            result = filmService.getAllFilmsWithDirectors(pageRequest);
        } else {
            result = filmService.getAllNotDeletedFilmsWithDirectors(pageRequest);
        }
        model.addAttribute("films", result);
        model.addAttribute("exception", exception);
        return "films/viewAllFilms";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model) {
        model.addAttribute("film", filmService.getFilmWithDirectors(id));
        return "films/viewFilm";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO,
                         @RequestParam MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            filmService.create(filmDTO, file);
        }
        else {
            filmService.create(filmDTO);
        }
        return "redirect:/films";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        model.addAttribute("film", filmService.getOne(id));
        return "films/updateFilm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("filmForm") FilmDTO filmDTO,
                         @RequestParam MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            filmService.update(filmDTO, file);
        }
        else {
            filmService.update(filmDTO);
        }
        return "redirect:/films";
    }
    @GetMapping("/add-director/{filmId}")
    public String addDirector(@PathVariable Long filmId,
                              Model model) {
        model.addAttribute("directors", directorService.listAll());
        model.addAttribute("filmId", filmId);
        model.addAttribute("film", filmService.getOne(filmId).getFilmTitle());
        return "films/addFilmDirector";
    }

    @PostMapping("/add-director")
    public String addDirector(@ModelAttribute("filmDirectorForm") AddDirectorDTO addDirectorDTO) {
        filmService.addDirector(addDirectorDTO);
        return "redirect:/films";
    }
    @GetMapping("/add-composer/{filmId}")
    public String addComposer(@PathVariable Long filmId,
                              Model model) {
        model.addAttribute("composers", composerService.listAll());
        model.addAttribute("filmId", filmId);
        model.addAttribute("film", filmService.getOne(filmId).getFilmTitle());
        return "films/addFilmComposer";
    }

    @PostMapping("/add-composer")
    public String addComposer(@ModelAttribute("filmComposerForm") AddComposerDTO addComposerDTO) {
        filmService.addComposer(addComposerDTO);
        return "redirect:/films";
    }


    @PostMapping("/search")
    public String searchFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("filmSearchForm") FilmSearchDTO filmSearchDTO,
                              Model model) {
        log.info(filmSearchDTO.toString());
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "title"));
        model.addAttribute("films", filmService.findFilms(filmSearchDTO, pageRequest));
        return "films/viewAllFilms";
    }

    @PostMapping("/search/director")
    public String searchFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("directorSearchForm") DirectorDTO directorDTO,
                              Model model) {
        FilmSearchDTO filmSearchDTO = new FilmSearchDTO();
        filmSearchDTO.setDirectorsFio(directorDTO.getDirectorsFio());
        return searchFilms(page, pageSize, filmSearchDTO, model);
    }
    @PostMapping("/search/composer")
    public String searchFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("composerSearchForm") ComposerDTO composerDTO,
                              Model model) {
        FilmSearchDTO filmSearchDTO = new FilmSearchDTO();
        filmSearchDTO.setComposersFio(composerDTO.getComposersFio());
        return searchFilms(page, pageSize, filmSearchDTO, model);
    }

    @GetMapping(value = "/download", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFilm(@Param(value = "filmId") Long filmId) throws IOException {
        FilmDTO filmDTO = filmService.getOne(filmId);
        Path path = Paths.get(filmDTO.getOnlineCopyPath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(this.headers(path.getFileName().toString()))
                .contentLength(path.toFile().length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    private HttpHeaders headers(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        filmService.deleteSoft(id);
        return "redirect:/films";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        filmService.restore(id);
        return "redirect:/films";
    }
//    @GetMapping("/rate/film/{filmId}")
//    public String rateFilm(@PathVariable Long filmId,
//                           Model model) {
//        model.addAttribute("film", filmService.getOne(filmId));
//        return "userFilms/rateFilm";
//    }
//
//    @PostMapping("/rate/film")
//
//    public String rateFilm(@ModelAttribute("rateFilmForm") FilmRateDTO filmRateDTO) {
//        log.info(filmRateDTO.toString());
//        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        filmRateDTO.setUserId(Long.valueOf(customUserDetails.getUserId()));
//        filmService.rateFilm(filmRateDTO);
////        return "redirect:/rate/user-films/" + customUserDetails.getUserId();
////        return "redirect:films/viewAllFilms";
//        return "films/viewAllFilms";
//    }
    @ExceptionHandler({MyDeleteException.class, AccessDeniedException.class})
    public RedirectView handleError(HttpServletRequest req,
                                    Exception ex,
                                    RedirectAttributes redirectAttributes) {
        log.error("Запрос: " + req.getRequestURL() + " вызвал ошибку " + ex.getMessage());
        redirectAttributes.addFlashAttribute("exception", ex.getMessage());
        return new RedirectView("/films", true);
    }
}


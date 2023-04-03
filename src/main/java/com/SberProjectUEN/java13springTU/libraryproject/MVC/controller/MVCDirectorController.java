package com.SberProjectUEN.java13springTU.libraryproject.MVC.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.*;
import com.SberProjectUEN.java13springTU.libraryproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.libraryproject.service.DirectorService;
import com.SberProjectUEN.java13springTU.libraryproject.service.FilmService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import static com.SberProjectUEN.java13springTU.libraryproject.constants.UserRolesConstants.ADMIN;

@Controller
@Hidden
@RequestMapping("/directors")
@Slf4j
public class MVCDirectorController {

    private final DirectorService directorService;
    private final FilmService filmService;


    public MVCDirectorController(DirectorService directorService,
                                 FilmService filmService) {
        this.directorService = directorService;
        this.filmService = filmService;
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "directorsFio"));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<DirectorDTO> result;
        if (ADMIN.equalsIgnoreCase(userName)) {
            result = directorService.listAll(pageRequest);
        }
        else {
            result = directorService.listAllNotDeleted(pageRequest);
        }
        model.addAttribute("directors", result);
        model.addAttribute("exception", exception);
        return "directors/viewAllDirectors";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model) {
        model.addAttribute("director", directorService.getOne(id));
        return "directors/viewDirector";
    }

    @GetMapping("/add")
    public String create() {
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorForm") DirectorDTO directorDTO) {
        directorService.create(directorDTO);
        return "redirect:/directors";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        model.addAttribute("director", directorService.getOne(id));
        return "directors/updateDirector";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("directorForm") DirectorDTO directorDTO) {

        directorService.update(directorDTO);
        return "redirect:/directors";
    }

    @GetMapping("/add-film/{directorId}")
    public String addFilm(@PathVariable Long directorId,
                          Model model) {
        model.addAttribute("films", filmService.listAll());
        model.addAttribute("directorId", directorId);
        model.addAttribute("director", directorService.getOne(directorId).getDirectorsFio());
        return "directors/addDirectorFilm";
    }

    @PostMapping("/add-film")
    public String addFilm(@ModelAttribute("directorFilmForm") AddFilmDTO addFilmDTO) {
        directorService.addFilm(addFilmDTO);
        return "redirect:/directors";
    }

    @PostMapping("/search")
    public String searchFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("directorSearchForm") DirectorDTO directorDTO,
                              Model model) {
        if (StringUtils.hasText(directorDTO.getDirectorsFio()) || StringUtils.hasLength(directorDTO.getDirectorsFio())) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "directorsFio"));
            model.addAttribute("directors", directorService.searchDirectors(directorDTO.getDirectorsFio().trim(), pageRequest));
            return "directors/viewAllDirectors";
        }
        else {
            return "redirect:/directors";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        directorService.delete(id);
        return "redirect:/directors";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        directorService.restore(id);
        return "redirect:/directors";
    }

    @ExceptionHandler(MyDeleteException.class)
    public RedirectView handleError(HttpServletRequest req,
                                    Exception ex,
                                    RedirectAttributes redirectAttributes) {
        log.error("Запрос: " + req.getRequestURL() + " вызвал ошибку " + ex.getMessage());
        redirectAttributes.addFlashAttribute("exception", ex.getMessage());
        return new RedirectView("/directors", true);
    }
}

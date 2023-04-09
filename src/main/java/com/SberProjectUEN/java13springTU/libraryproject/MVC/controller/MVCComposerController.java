package com.SberProjectUEN.java13springTU.libraryproject.MVC.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.*;
import com.SberProjectUEN.java13springTU.libraryproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.libraryproject.service.ComposerService;
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
@RequestMapping("/composers")
@Slf4j
public class MVCComposerController {

    private final ComposerService composerService;
    private final FilmService filmService;


    public MVCComposerController(ComposerService composerService,
                                 FilmService filmService) {
        this.composerService = composerService;
        this.filmService = filmService;
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "composersFio"));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<ComposerDTO> result;
        if (ADMIN.equalsIgnoreCase(userName)) {
            result = composerService.listAll(pageRequest);
        }
        else {
            result = composerService.listAllNotDeleted(pageRequest);
        }
        model.addAttribute("composers", result);
        model.addAttribute("exception", exception);
        return "composers/viewAllComposers";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id,
                         Model model) {
        model.addAttribute("composer", composerService.getOne(id));
        return "composers/viewComposer";
    }

    @GetMapping("/add")
    public String create() {
        return "composers/addComposer";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("composerForm") ComposerDTO composerDTO) {
        composerService.create(composerDTO);
        return "redirect:/composers";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) {
        model.addAttribute("composer", composerService.getOne(id));
        return "composers/updateComposer";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("composerForm") ComposerDTO composerDTO) {

        composerService.update(composerDTO);
        return "redirect:/composers";
    }

    @GetMapping("/add-film/{composerId}")
    public String addFilm(@PathVariable Long composerId,
                          Model model) {
        model.addAttribute("films", filmService.listAll());
        model.addAttribute("composerId", composerId);
        model.addAttribute("composer", composerService.getOne(composerId).getComposersFio());
        return "composers/addComposerFilm";
    }

    @PostMapping("/add-film")
    public String addFilm(@ModelAttribute("composerFilmForm") AddFilmDTO addFilmDTO) {
        composerService.addFilm(addFilmDTO);
        return "redirect:/composers";
    }

    @PostMapping("/search")
    public String searchFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int pageSize,
                              @ModelAttribute("composerSearchForm") ComposerDTO composerDTO,
                              Model model) {
        if (StringUtils.hasText(composerDTO.getComposersFio()) || StringUtils.hasLength(composerDTO.getComposersFio())) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "composersFio"));
            model.addAttribute("composers", composerService.searchComposers(composerDTO.getComposersFio().trim(), pageRequest));
            return "composers/viewAllComposers";
        }
        else {
            return "redirect:/composers";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        composerService.delete(id);
        return "redirect:/composers";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        composerService.restore(id);
        return "redirect:/composers";
    }

    @ExceptionHandler(MyDeleteException.class)
    public RedirectView handleError(HttpServletRequest req,
                                    Exception ex,
                                    RedirectAttributes redirectAttributes) {
        log.error("Запрос: " + req.getRequestURL() + " вызвал ошибку " + ex.getMessage());
        redirectAttributes.addFlashAttribute("exception", ex.getMessage());
        return new RedirectView("/composers", true);
    }
}



package com.SberProjectUEN.java13springTU.libraryproject.MVC.controller;


import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmWithDirectorsDTO;
import com.SberProjectUEN.java13springTU.libraryproject.service.FilmService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Hidden
@Controller
@RequestMapping("films")
public class MVCFilmController {
    private final FilmService filmService;

    public MVCFilmController(FilmService filmService) {

        this.filmService = filmService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<FilmWithDirectorsDTO> result = filmService.getAllFilmsWithDirectors();
        model.addAttribute("films", result);
        return "films/viewAllFilms";
    }

    @GetMapping("/add")
    public String create() {
        return "films/addFilm";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("filmForm") FilmDTO filmDTO) {
        filmService.create(filmDTO);
        return "redirect:/films";
    }
}

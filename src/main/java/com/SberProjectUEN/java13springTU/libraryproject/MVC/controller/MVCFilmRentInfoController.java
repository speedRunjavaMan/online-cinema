package com.SberProjectUEN.java13springTU.libraryproject.MVC.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.service.FilmRentInfoService;
import com.SberProjectUEN.java13springTU.libraryproject.service.FilmService;
import com.SberProjectUEN.java13springTU.libraryproject.service.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@Hidden
@RequestMapping("/rent")
public class MVCFilmRentInfoController {
    private final FilmRentInfoService filmRentInfoService;
    private final FilmService filmService;

    public MVCFilmRentInfoController(FilmRentInfoService filmRentInfoService, FilmService filmService) {
        this.filmRentInfoService = filmRentInfoService;
        this.filmService = filmService;
    }

    @GetMapping("/film/{filmId}")
    public String rentFilm(@PathVariable Long filmId,
                           Model model) {
        model.addAttribute("film", filmService.getOne(filmId));
        return "userFilms/rentFilm";
    }

    @PostMapping("/film")
    public String rentFilm(@ModelAttribute("rentFilmForm") FilmRentInfoDTO rentFilmDTO) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        rentFilmDTO.setUserId(Long.valueOf(customUserDetails.getUserId()));
        filmRentInfoService.rentFilm(rentFilmDTO);
        return "redirect:/rent/user-films/" + customUserDetails.getUserId();
    }

    @GetMapping("/user-films/{id}")
    public String userFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "5") int pageSize,
                            @PathVariable Long id,
                            Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<FilmRentInfoDTO> rentInfoDTOPage = filmRentInfoService.listUserRentFilms(id, pageRequest);
        model.addAttribute("rentFilms", rentInfoDTOPage);
        return "userFilms/viewAllUserFilms";
    }

    @GetMapping("/return-film/{id}")
    public String returnFilm(@PathVariable Long id) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        filmRentInfoService.returnFilm(id);
        return "redirect:/rent/user-films/" + customUserDetails.getUserId();
    }
}


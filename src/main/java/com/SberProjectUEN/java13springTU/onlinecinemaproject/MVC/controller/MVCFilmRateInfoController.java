package com.SberProjectUEN.java13springTU.onlinecinemaproject.MVC.controller;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmRateInfoDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.FilmRateInfoService;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.FilmService;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.userdetails.CustomUserDetails;
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
@RequestMapping("/rate")
public class MVCFilmRateInfoController {
    private final FilmRateInfoService filmRateInfoService;
    private final FilmService filmService;

    public MVCFilmRateInfoController(FilmRateInfoService filmRateInfoService, FilmService filmService) {
        this.filmRateInfoService = filmRateInfoService;
        this.filmService = filmService;
    }

    @GetMapping("/film/{filmId}")
    public String rateFilm(@PathVariable Long filmId,
                           Model model) {
        model.addAttribute("film", filmService.getOne(filmId));
        return "userFilms/rateFilm";
    }

    @PostMapping("/film")
    public String rateFilm(@ModelAttribute("rateFilmForm") FilmRateInfoDTO rateFilmDTO) {
        log.info(rateFilmDTO.toString());
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        rateFilmDTO.setUserId(Long.valueOf(customUserDetails.getUserId()));
        filmRateInfoService.rateFilm(rateFilmDTO);
        return "redirect:/rate/user-films/" + customUserDetails.getUserId();
    }

    @GetMapping("/user-films/{id}")
    public String userFilms(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "5") int pageSize,
                            @ModelAttribute(name = "exception") final String exception,
                            @PathVariable Long id,
                            Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<FilmRateInfoDTO> rateInfoDTOPage = filmRateInfoService.listUserRateFilms(id, pageRequest);
        model.addAttribute("rateFilms", rateInfoDTOPage);
        model.addAttribute("exception", exception);
        return "userFilms/viewAllRatedUserFilms";
    }

    @GetMapping("/return-film/{id}")
    public String returnFilm(@PathVariable Long id) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        filmRateInfoService.returnFilm(id);
        return "redirect:/rate/user-films/" + customUserDetails.getUserId();
    }

    //    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) throws MyDeleteException {
//        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        FilmRateInfoDTO rateFilmDTO = new FilmRateInfoDTO();
//        rateFilmDTO = filmRateInfoService.getOne(id);
//        filmRateInfoService.deleteHard(rateFilmDTO.getId());
//        return "redirect:/rate/user-films/" + customUserDetails.getUserId();
//    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        filmRateInfoService.deleteHard(id);
        return "userFilms/viewAllRatedUserFilms";
    }
//    @GetMapping("/delete/{id}")
//    public String delete(@RequestParam(value = "page", defaultValue = "1") int page,
//                            @RequestParam(value = "size", defaultValue = "5") int pageSize,
//                            @ModelAttribute(name = "exception") final String exception,
//                            @PathVariable Long id,
//                            Model model) {
//
//        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
//        Page<FilmRateInfoDTO> rateInfoDTOPage = filmRateInfoService.listUserRateFilms(id, pageRequest);
//        model.addAttribute("rateFilms", rateInfoDTOPage);
//        model.addAttribute("exception", exception);
//        filmRateInfoService.deleteHard(id);
//        return "userFilms/viewAllRatedUserFilms";
//    }
}



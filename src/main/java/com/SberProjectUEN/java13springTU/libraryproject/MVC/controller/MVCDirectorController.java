package com.SberProjectUEN.java13springTU.libraryproject.MVC.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorWithFilmsDTO;
import com.SberProjectUEN.java13springTU.libraryproject.service.DirectorService;
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
@RequestMapping("directors")
public class MVCDirectorController {
    private final DirectorService directorService;

    public MVCDirectorController(DirectorService directorService) {

        this.directorService = directorService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<DirectorWithFilmsDTO> result = directorService.getAllDirectorsWithFilms();
        model.addAttribute("directors", result);
        return "directors/viewAllDirectors";
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
}

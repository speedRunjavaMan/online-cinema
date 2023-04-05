package com.SberProjectUEN.java13springTU;

import com.SberProjectUEN.java13springTU.libraryproject.dto.ComposerDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmWithDirectorsDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.Genre;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface FilmTestData {
    FilmDTO FILM_DTO_1 = new FilmDTO("filmTitle1",
            "premierYear1",
            "country1",
            Genre.DRAMA,
            "onlineCopyPath1",
            false,
            new HashSet<>(),
            new HashSet<>());
    FilmDTO FILM_DTO_2 = new FilmDTO("filmTitle2",
            "premierYear2",
            "country2",
            Genre.FANTASY,
            "onlineCopyPath2",
            false,
            new HashSet<>(),
            new HashSet<>());

    List<FilmDTO> FILM_DTO_LIST = Arrays.asList(FILM_DTO_1, FILM_DTO_2);

    Film FILM_1 = new Film(
            "filmTitle1",
            LocalDate.now(),
            "country1",
            "onlineCopyPath1",
            Genre.FANTASY,
            new HashSet<>(),
            new HashSet<>(),
            new HashSet<>());

    Film FILM_2 = new Film(
            "filmTitle2",
            LocalDate.now(),
            "country1",
            "onlineCopyPath1",
            Genre.DRAMA,
            new HashSet<>(),
            new HashSet<>(),
            new HashSet<>());

    List<Film> FILM_LIST = Arrays.asList(FILM_1, FILM_2);


    Set<DirectorDTO> DIRECTORS = new HashSet<>(DirectorTestData.DIRECTOR_DTO_LIST);
    Set<ComposerDTO> COMPOSERS = new HashSet<>(ComposerTestData.COMPOSER_DTO_LIST);
    FilmWithDirectorsDTO FILM_WITH_DIRECTORS_DTO_1 = new FilmWithDirectorsDTO(FILM_1, DIRECTORS, COMPOSERS);
    FilmWithDirectorsDTO FILM_WITH_DIRECTORS_DTO_2 = new FilmWithDirectorsDTO(FILM_2, DIRECTORS, COMPOSERS);

    List<FilmWithDirectorsDTO> FILM_WITH_DIRECTORS_DTO_LIST = Arrays.asList(FILM_WITH_DIRECTORS_DTO_1, FILM_WITH_DIRECTORS_DTO_2);
}

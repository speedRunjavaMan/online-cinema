package com.SberProjectUEN.java13springTU.libraryproject.dto;

import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmWithDirectorsDTO
        extends FilmDTO {
    public FilmWithDirectorsDTO(Film film, Set<DirectorDTO> directors) {
        super(film);
        this.directors = directors;
    }

    private Set<DirectorDTO> directors;
}

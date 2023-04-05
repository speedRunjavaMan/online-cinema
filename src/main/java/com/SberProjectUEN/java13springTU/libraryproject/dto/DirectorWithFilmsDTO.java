package com.SberProjectUEN.java13springTU.libraryproject.dto;

import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorWithFilmsDTO
        extends DirectorDTO {
    public DirectorWithFilmsDTO(Director director, Set<FilmDTO> films) {
        super(director);
        this.films = films;
    }
    private Set<FilmDTO> films;
}

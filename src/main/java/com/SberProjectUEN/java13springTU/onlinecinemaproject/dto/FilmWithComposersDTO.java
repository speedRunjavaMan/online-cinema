package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmWithComposersDTO
        extends FilmDTO {
    public FilmWithComposersDTO(Film film, Set<DirectorDTO> directors, Set<ComposerDTO> composers) {
        super(film);
        this.directors = directors;
        this.composers = composers;
    }

    private Set<DirectorDTO> directors;
    private Set<ComposerDTO> composers;
}

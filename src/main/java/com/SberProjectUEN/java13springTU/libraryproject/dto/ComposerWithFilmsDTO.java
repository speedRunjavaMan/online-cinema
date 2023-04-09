package com.SberProjectUEN.java13springTU.libraryproject.dto;

import com.SberProjectUEN.java13springTU.libraryproject.model.Composer;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComposerWithFilmsDTO
        extends ComposerDTO {
    public ComposerWithFilmsDTO(Composer composer, Set<FilmDTO> films) {
        super(composer);
        this.films = films;
    }
    private Set<FilmDTO> films;
}


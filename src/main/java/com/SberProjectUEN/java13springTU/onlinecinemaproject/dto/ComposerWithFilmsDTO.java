package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Composer;
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


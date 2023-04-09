package com.SberProjectUEN.java13springTU.libraryproject.dto;

import com.SberProjectUEN.java13springTU.libraryproject.model.Composer;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComposerDTO
        extends GenericDTO {
    private String composersFio;
    private String position;
    private boolean isDeleted;
    private Set<Long> filmsIds;
    public Set<Long> getFilmsIds() {
        return filmsIds;
    }


    public ComposerDTO(Composer composer) {
        ComposerDTO composerDTO = new ComposerDTO();
        //из entity делаем DTO
        composerDTO.setComposersFio(composer.getComposersFio());
        composerDTO.setPosition(composer.getPosition());
        composerDTO.setId(composer.getId());
        Set<Film> films = composer.getFilms();
        Set<Long> filmIds = new HashSet<>();
        if (films != null && films.size() > 0) {
            films.forEach(a -> filmIds.add(a.getId()));
        }
        composerDTO.setFilmsIds(filmIds);
    }
}



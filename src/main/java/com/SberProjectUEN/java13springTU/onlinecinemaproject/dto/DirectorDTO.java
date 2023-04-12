package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Director;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectorDTO
        extends GenericDTO {
    private String directorsFio;
    private String position;
    private boolean isDeleted;
    private Set<Long> filmsIds;
    public Set<Long> getFilmsIds() {
        return filmsIds;
    }


    public DirectorDTO(Director director) {
        DirectorDTO directorDTO = new DirectorDTO();
        //из entity делаем DTO
        directorDTO.setDirectorsFio(director.getDirectorsFio());
        directorDTO.setPosition(director.getPosition());
        directorDTO.setId(director.getId());
        Set<Film> films = director.getFilms();
        Set<Long> filmIds = new HashSet<>();
        if (films != null && films.size() > 0) {
            films.forEach(a -> filmIds.add(a.getId()));
        }
        directorDTO.setFilmsIds(filmIds);
    }
}

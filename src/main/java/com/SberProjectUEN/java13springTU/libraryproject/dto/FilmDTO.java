package com.SberProjectUEN.java13springTU.libraryproject.dto;

import com.SberProjectUEN.java13springTU.libraryproject.model.Composer;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.Genre;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO
      extends GenericDTO {
  
  private String filmTitle;
  private String premierYear;
  private String country;
  private Genre genre;
  private String onlineCopyPath;
  private boolean isDeleted;

  public Set<Long> getDirectorsIds() {
    return directorsIds;
  }
  public Set<Long> getComposersIds() {
    return composersIds;
  }
  private Set<Long> directorsIds;
  private Set<Long> composersIds;
  
      public FilmDTO(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        //из entity делаем DTO
        filmDTO.setFilmTitle(film.getFilmTitle());
        filmDTO.setGenre(film.getGenre());
        filmDTO.setCountry(film.getCountry());
        filmDTO.setId(film.getId());
        //filmDTO.setPageCount(film.getPageCount());
        filmDTO.setPremierYear(film.getPremierYear().toString());
        Set<Director> directors = film.getDirectors();
        Set<Long> directorIds = new HashSet<>();
        if (directors != null && directors.size() > 0) {
          directors.forEach(a -> directorIds.add(a.getId()));
        }
        filmDTO.setDirectorsIds(directorIds);
        Set<Composer> composers = film.getComposers();
        Set<Long> composerIds = new HashSet<>();
        if (composers != null && composers.size() > 0) {
          composers.forEach(a -> composerIds.add(a.getId()));
        }
        filmDTO.setComposersIds(composerIds);
      }
}

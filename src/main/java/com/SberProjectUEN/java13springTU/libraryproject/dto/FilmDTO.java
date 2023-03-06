package com.SberProjectUEN.java13springTU.libraryproject.dto;

import com.SberProjectUEN.java13springTU.libraryproject.model.Genre;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FilmDTO
      extends GenericDTO {
  
  private String filmTitle;
  private LocalDate premierYear;
  private String country;
  private Genre genre;

  public Set<Long> getDirectorsIds() {
    return directorsIds;
  }

  private Set<Long> directorsIds;
  
  //    public FilmDTO(Film film) {
//        FilmDTO filmDTO = new FilmDTO();
//        //из entity делаем DTO
//        filmDTO.setFilmTitle(film.getFilmTitle());
//        filmDTO.setGenre(film.getGenre());
//        filmDTO.setDescription(film.getDescription());
//        filmDTO.setId(film.getId());
//        filmDTO.setPageCount(film.getPageCount());
//        filmDTO.setPublishDate(film.getPublishDate());
//        Set<Director> directors = film.getDirectors();
//        Set<Long> directorsIds = new HashSet<>();
//        directors.forEach(a -> directorIds.add(a.getId()));
//        filmDTO.setDirectorsIds(directorIds);
//    }
//
//    public List<BookDTO> getBookDTOs(List<Book> books) {
//        List<BookDTO> bookDTOS = new ArrayList<>();
//        for (Book book : books) {
//            bookDTOS.add(new BookDTO(book));
//        }
//        return bookDTOS;
//    }
}

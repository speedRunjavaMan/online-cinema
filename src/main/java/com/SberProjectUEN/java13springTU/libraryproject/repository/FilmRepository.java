package com.SberProjectUEN.java13springTU.libraryproject.repository;

import com.SberProjectUEN.java13springTU.libraryproject.model.Film;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository
      extends GenericRepository<Film> {
    List<Film> findFilmsByDirectors(Film film);

}

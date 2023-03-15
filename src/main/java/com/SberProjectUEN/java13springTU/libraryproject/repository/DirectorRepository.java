package com.SberProjectUEN.java13springTU.libraryproject.repository;

import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository
      extends GenericRepository<Director> {
    List<Director> findDirectorsByFilms(Director director);
}

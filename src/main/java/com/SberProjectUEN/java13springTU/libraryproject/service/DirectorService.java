package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.constants.Errors;
import com.SberProjectUEN.java13springTU.libraryproject.dto.AddFilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.DirectorMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.libraryproject.repository.DirectorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DirectorService
        extends GenericService<Director, DirectorDTO> {

    private final DirectorRepository directorRepository;

    private final FilmService filmService;

    protected DirectorService(DirectorRepository directorRepository,
                              DirectorMapper directorMapper,
                              FilmService filmService) {
        super(directorRepository, directorMapper);
        this.directorRepository = directorRepository;
        this.filmService = filmService;
    }

    public Page<DirectorDTO> searchDirectors(final String fio,
                                             Pageable pageable) {
        Page<Director> directors = directorRepository.findAllByDirectorsFioContainsIgnoreCaseAndIsDeletedFalse(fio, pageable);
        List<DirectorDTO> result = mapper.toDTOs(directors.getContent());
        return new PageImpl<>(result, pageable, directors.getTotalElements());
    }

    public void addFilm(AddFilmDTO addFilmDTO) {
        DirectorDTO director = getOne(addFilmDTO.getDirectorId());
        filmService.getOne(addFilmDTO.getFilmId());
        director.getFilmsIds().add(addFilmDTO.getFilmId());
        update(director);
    }

    @Override
    public void delete(Long objectId) throws MyDeleteException {
        Director director = directorRepository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Режиссера с заданным id=" + objectId + " не существует."));
        boolean directorCanBeDeleted = directorRepository.checkDirectorForDeletion(objectId);
        if (directorCanBeDeleted) {
            markAsDeleted(director);
            Set<Film> films = director.getFilms();
            if (films != null && films.size() > 0) {
                films.forEach(this::markAsDeleted);
            }
            directorRepository.save(director);
        } else {
            throw new MyDeleteException(Errors.Directors.DIRECTOR_DELETE_ERROR);
        }
    }

    public void restore(Long objectId) {
        Director director = directorRepository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Режиссера с заданным id=" + objectId + " не существует."));
        unMarkAsDeleted(director);
        Set<Film> films = director.getFilms();
        if (films != null && films.size() > 0) {
            films.forEach(this::unMarkAsDeleted);
        }
        directorRepository.save(director);
    }
}


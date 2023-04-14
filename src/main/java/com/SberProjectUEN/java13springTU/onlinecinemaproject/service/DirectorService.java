package com.SberProjectUEN.java13springTU.onlinecinemaproject.service;


import com.SberProjectUEN.java13springTU.onlinecinemaproject.constants.Errors;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.AddFilmDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper.DirectorMapper;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Director;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.DirectorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


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
    public void deleteSoft(Long objectId) throws MyDeleteException {
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


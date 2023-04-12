package com.SberProjectUEN.java13springTU.onlinecinemaproject.service;


import com.SberProjectUEN.java13springTU.onlinecinemaproject.constants.Errors;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.AddFilmDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.ComposerDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper.ComposerMapper;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Composer;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.ComposerRepository;
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
public class ComposerService
        extends GenericService<Composer, ComposerDTO> {

    private final ComposerRepository composerRepository;

    private final FilmService filmService;

    protected ComposerService(ComposerRepository composerRepository,
                              ComposerMapper composerMapper,
                              FilmService filmService) {
        super(composerRepository, composerMapper);
        this.composerRepository = composerRepository;
        this.filmService = filmService;
    }

    public Page<ComposerDTO> searchComposers(final String fio,
                                             Pageable pageable) {
        Page<Composer> composers = composerRepository.findAllByComposersFioContainsIgnoreCaseAndIsDeletedFalse(fio, pageable);
        List<ComposerDTO> result = mapper.toDTOs(composers.getContent());
        return new PageImpl<>(result, pageable, composers.getTotalElements());
    }

    public void addFilm(AddFilmDTO addFilmDTO) {
        ComposerDTO composer = getOne(addFilmDTO.getComposerId());
        filmService.getOne(addFilmDTO.getFilmId());
        composer.getFilmsIds().add(addFilmDTO.getFilmId());
        update(composer);
    }

    @Override
    public void delete(Long objectId) throws MyDeleteException {
        Composer composer = composerRepository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Композитора с заданным id=" + objectId + " не существует."));
        boolean composerCanBeDeleted = composerRepository.checkComposerForDeletion(objectId);
        if (composerCanBeDeleted) {
            markAsDeleted(composer);
            Set<Film> films = composer.getFilms();
            if (films != null && films.size() > 0) {
                films.forEach(this::markAsDeleted);
            }
            composerRepository.save(composer);
        } else {
            throw new MyDeleteException(Errors.Composers.COMPOSER_DELETE_ERROR);
        }
    }

    public void restore(Long objectId) {
        Composer composer = composerRepository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Композитора с заданным id=" + objectId + " не существует."));
        unMarkAsDeleted(composer);
        Set<Film> films = composer.getFilms();
        if (films != null && films.size() > 0) {
            films.forEach(this::unMarkAsDeleted);
        }
        composerRepository.save(composer);
    }
}



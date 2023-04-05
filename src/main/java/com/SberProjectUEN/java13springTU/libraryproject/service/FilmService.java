package com.SberProjectUEN.java13springTU.libraryproject.service;

import com.SberProjectUEN.java13springTU.libraryproject.constants.Errors;
import com.SberProjectUEN.java13springTU.libraryproject.dto.*;
import com.SberProjectUEN.java13springTU.libraryproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.FilmMapper;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.FilmWithDirectorsMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRepository;
import com.SberProjectUEN.java13springTU.libraryproject.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FilmService
        extends GenericService<Film, FilmDTO> {
    //  Инжектим конкретный репозиторий для работы с таблицей books
    private final FilmRepository repository;
    private final FilmWithDirectorsMapper filmWithDirectorsMapper;
//    private final DirectorService directorService;
    protected FilmService(FilmRepository repository,
                          FilmMapper mapper,
//                          DirectorService directorService,
                          FilmWithDirectorsMapper filmWithDirectorsMapper
                          ) {
        //Передаем этот репозиторий в абстрактный сервис,
        //чтобы он понимал с какой таблицей будут выполняться CRUD операции
        super(repository, mapper);
        this.repository = repository;
        this.filmWithDirectorsMapper = filmWithDirectorsMapper;
//        this.directorService = directorService;
    }

    public FilmDTO addDirectorToFilm(Long filmId, Long directorId) {
        FilmDTO object = this.getOne(filmId);
        Set<Long> dir = object.getDirectorsIds();
        dir.add(directorId);
        object.setDirectorsIds(dir);
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }
    public void addDirector(AddDirectorDTO addDirectorDTO) {
        FilmDTO film = getOne(addDirectorDTO.getFilmId());
//        directorService.getOne(addDirectorDTO.getDirectorId());
        film.getDirectorsIds().add(addDirectorDTO.getDirectorId());
        update(film);
    }
    public void addComposer(AddComposerDTO addComposerDTO) {
        FilmDTO film = getOne(addComposerDTO.getFilmId());
//        composerService.getOne(addComposerDTO.getComposerId());
        film.getComposersIds().add(addComposerDTO.getComposerId());
        update(film);
    }

    public Page<FilmWithDirectorsDTO> getAllFilmsWithDirectors(Pageable pageable) {
        Page<Film> filmsPaginated = repository.findAll(pageable);
        List<FilmWithDirectorsDTO> result = filmWithDirectorsMapper.toDTOs(filmsPaginated.getContent());
        return new PageImpl<>(result, pageable, filmsPaginated.getTotalElements());
    }

    public Page<FilmWithDirectorsDTO> getAllNotDeletedFilmsWithDirectors(Pageable pageable) {
        Page<Film> filmsPaginated = repository.findAllByIsDeletedFalse(pageable);
        List<FilmWithDirectorsDTO> result = filmWithDirectorsMapper.toDTOs(filmsPaginated.getContent());
        return new PageImpl<>(result, pageable, filmsPaginated.getTotalElements());
    }

    public FilmWithDirectorsDTO getFilmWithDirectors(Long id) {
        return filmWithDirectorsMapper.toDTO(mapper.toEntity(super.getOne(id)));
    }

    public Page<FilmWithDirectorsDTO> findFilms(FilmSearchDTO filmSearchDTO,
                                                Pageable pageable) {
        String genre = filmSearchDTO.getGenre() != null ? String.valueOf(filmSearchDTO.getGenre().ordinal()) : null;
        Page<Film> filmsPaginated = repository.searchFilms(genre,
                                                            filmSearchDTO.getFilmTitle(),
                                                            filmSearchDTO.getDirectorsFio(),
                                                            filmSearchDTO.getComposersFio(),
                                                            pageable
                                                            );
        List<FilmWithDirectorsDTO> result = filmWithDirectorsMapper.toDTOs(filmsPaginated.getContent());
        return new PageImpl<>(result, pageable, filmsPaginated.getTotalElements());
    }

    // files/books/year/month/day/file_name_{id}_{created_when}.txt
    // files/книга_id.pdf
    public FilmDTO create(final FilmDTO object,
                          MultipartFile file)
    {
        String fileName = FileHelper.createFile(file);
        object.setOnlineCopyPath(fileName);
        object.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        object.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    public FilmDTO update(final FilmDTO object,
                          MultipartFile file)
    {
        String fileName = FileHelper.createFile(file);
        object.setOnlineCopyPath(fileName);
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    @Override
    public void delete(Long id) throws MyDeleteException {
        Film film = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Фильма с заданным ID=" + id + " не существует"));
//        boolean filmCanBeDeleted = repository.findFilmByIdAndFilmRentInfosReturnedFalseAndIsDeletedFalse(id) == null;
        boolean filmCanBeDeleted = repository.checkFilmForDeletion(id);
        if (filmCanBeDeleted) {
            if (film.getOnlineCopyPath() != null && !film.getOnlineCopyPath().isEmpty()) {
                FileHelper.deleteFile(film.getOnlineCopyPath());
            }
            markAsDeleted(film);
            repository.save(film);
        } else {
            throw new MyDeleteException(Errors.Films.FILM_DELETE_ERROR);
        }
    }

    public void restore(Long objectId) {
        Film film = repository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Фильма с заданным ID=" + objectId + " не существует"));
        film.setDeleted(false);
        film.setDeletedWhen(null);
        film.setDeletedBy(null);
        repository.save(film);
    }
}
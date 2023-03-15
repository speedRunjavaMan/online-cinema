package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorWithFilmsDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmWithDirectorsDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.DirectorMapper;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.DirectorWithFilmsMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DirectorService
        extends GenericService<Director, DirectorDTO> {
    private final DirectorRepository repository;
    private final DirectorWithFilmsMapper directorWithFilmsMapper;

    protected DirectorService(DirectorRepository repository,
                              DirectorMapper mapper, DirectorWithFilmsMapper directorWithFilmsMapper) {
        //Передаем этот репозиторй в абстрактный севрис,
        //чтобы он понимал с какой таблицей будут выполняться CRUD операции
        super(repository, mapper);
        this.repository = repository;
        this.directorWithFilmsMapper = directorWithFilmsMapper;
    }

    public DirectorDTO addFilmToDirector(Long filmId, Long directorId) {
        DirectorDTO object = this.getOne(directorId);
        Set<Long> dir = object.getFilmsIds();
        dir.add(filmId);
        object.setFilmsIds(dir);
        return mapper.toDto(repository.save(mapper.toEntity(object)));
    }
    public List<DirectorWithFilmsDTO> getAllDirectorsWithFilms() {
        return directorWithFilmsMapper.toDTOs(repository.findAll());
    }
}


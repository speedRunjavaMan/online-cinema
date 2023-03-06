package com.SberProjectUEN.java13springTU.libraryproject.service;

import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.RoleDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.FilmMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilmService
        extends GenericService<Film, FilmDTO> {
    //  Инжектим конкретный репозиторий для работы с таблицей books
    private final FilmRepository repository;

    protected FilmService(FilmRepository repository,
                          FilmMapper mapper) {
        //Передаем этот репозиторй в абстрактный севрис,
        //чтобы он понимал с какой таблицей будут выполняться CRUD операции
        super(repository, mapper);
        this.repository = repository;
    }
//    @Override
//    public FilmDTO update(FilmDTO object) {
//        DirectorDTO directorDTO = new DirectorDTO();
//        directorDTO.setId(1L);
//        directorDTO.setDirectorsFio("Spilberg");
//        directorDTO.setPosition("Main");
//        Set<Long> dir = (object.getDirectorsIds());
//        dir.add(directorDTO.getId());
//        object.setDirectorsIds(dir);
//        return mapper.toDto(repository.save(mapper.toEntity(object)));
//    }

    public FilmDTO addDirectorToFilmById(Long directorId, Long filmId){
        FilmDTO filmDTO = this.getOne(filmId);
        Set<Long> directorsIds = filmDTO.getDirectorsIds();
        directorsIds.add(directorId);
        filmDTO.setDirectorsIds(directorsIds);
        this.update(filmDTO);
        return filmDTO;
    }






    //    public BookDTO getOne(Long id) {
//        return bookMapper.toDTO(bookRepository.findById(id).orElseThrow());
////        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Данных не существует по переданному id:" + id));
////        return new BookDTO(book);
//    }

//    public List<BookDTO> getAll() {
//        return new BookDTO(book);
//    }
}


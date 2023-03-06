package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.FilmRentInfoMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.FilmRentInfo;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRentInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmRentInfoService
        extends GenericService<FilmRentInfo, FilmRentInfoDTO> {
    private FilmRentInfoRepository filmRentInfoRepository;

    protected FilmRentInfoService(FilmRentInfoRepository filmRentInfoRepository,
                                  FilmRentInfoMapper filmRentInfoMapper) {
        super(filmRentInfoRepository, filmRentInfoMapper);
        this.filmRentInfoRepository = filmRentInfoRepository;
    }

//    public FilmRentInfoDTO makeOrderRent(Long filmId) {
//        FilmRentInfoDTO object = new FilmRentInfoDTO(filmId);
//        object.getFilmId();
//        Set<Long> dir = (filmId.getDirectorsIds());
//        dir.add(directorDTO.getId());
//        filmId.setDirectorsIds(dir);
//        return mapper.toDto(repository.save(mapper.toEntity(filmId)));
//    }




//    public FilmRentInfoDTO addDirectortoFilm(Long filmId, Long directorId) {
//        FilmRentInfoDTO filmRentInfoDTO = new FilmRentInfoDTO(filmId);
//        filmRentInfoDTO.
//                Set<Long> dir = (object.getDirectorsIds());
//        dir.add(directorDTO.getId());
//        object.setDirectorsIds(dir);
//        return mapper.toDto(repository.save(mapper.toEntity(object)));
//    }



}



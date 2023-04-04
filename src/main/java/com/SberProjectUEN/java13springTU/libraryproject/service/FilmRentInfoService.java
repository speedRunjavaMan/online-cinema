package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.FilmRentInfoMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.FilmRentInfo;
import com.SberProjectUEN.java13springTU.libraryproject.repository.FilmRentInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilmRentInfoService
        extends GenericService<FilmRentInfo, FilmRentInfoDTO> {
    private FilmService filmService;
    private final FilmRentInfoMapper filmRentInfoMapper;
    private final FilmRentInfoRepository filmRentInfoRepository;

    protected FilmRentInfoService(FilmRentInfoRepository filmRentInfoRepository,
                                  FilmRentInfoMapper filmRentInfoMapper,
                                  FilmService filmService) {
        super(filmRentInfoRepository, filmRentInfoMapper);
        this.filmService = filmService;
        this.filmRentInfoMapper = filmRentInfoMapper;
        this.filmRentInfoRepository = filmRentInfoRepository;
    }

    public Page<FilmRentInfoDTO> listUserRentFilms(final Long id,
                                                   final Pageable pageable) {
        Page<FilmRentInfo> objects = filmRentInfoRepository.getFilmRentInfoByUserId(id, pageable);
        List<FilmRentInfoDTO> results = filmRentInfoMapper.toDTOs(objects.getContent());
        return new PageImpl<>(results, pageable, objects.getTotalElements());
    }

    public FilmRentInfoDTO rentFilm(FilmRentInfoDTO rentFilmDTO) {
        FilmDTO filmDTO = filmService.getOne(rentFilmDTO.getFilmId());
        filmService.update(filmDTO);
        long rentPeriod = rentFilmDTO.getRentPeriod() != null ? rentFilmDTO.getRentPeriod() : 14L;
        rentFilmDTO.setRentDate(LocalDateTime.now());
        rentFilmDTO.setReturned(false);
        rentFilmDTO.setRentPeriod((int) rentPeriod);
        rentFilmDTO.setReturnDate(LocalDateTime.now().plusDays(rentPeriod));
        rentFilmDTO.setCreatedWhen(LocalDateTime.now());
        rentFilmDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDTO(repository.save(mapper.toEntity(rentFilmDTO)));
    }

    public void returnFilm(final Long id) {
        FilmRentInfoDTO filmRentInfoDTO = getOne(id);
        filmRentInfoDTO.setReturned(true);
        filmRentInfoDTO.setReturnDate(LocalDateTime.now());
        FilmDTO filmDTO = filmRentInfoDTO.getFilmDTO();
        // bookDTO.setAmount(bookDTO.getAmount() + 1);
        update(filmRentInfoDTO);
        filmService.update(filmDTO);
    }
}


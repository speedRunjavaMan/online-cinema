package com.SberProjectUEN.java13springTU.onlinecinemaproject.service;


import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmRateInfoDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper.FilmRateInfoMapper;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.FilmRateInfo;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRateInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilmRateInfoService
        extends GenericService<FilmRateInfo, FilmRateInfoDTO> {
    private FilmService filmService;
    private final FilmRateInfoMapper filmRateInfoMapper;
    private final FilmRateInfoRepository filmRateInfoRepository;

    protected FilmRateInfoService(FilmRateInfoRepository filmRateInfoRepository,
                                  FilmRateInfoMapper filmRateInfoMapper,
                                  FilmService filmService) {
        super(filmRateInfoRepository, filmRateInfoMapper);
        this.filmService = filmService;
        this.filmRateInfoMapper = filmRateInfoMapper;
        this.filmRateInfoRepository = filmRateInfoRepository;
    }

    public Page<FilmRateInfoDTO> listUserRateFilms(final Long id,
                                                   final Pageable pageable) {
        Page<FilmRateInfo> objects = filmRateInfoRepository.getFilmRateInfoByUserId(id, pageable);
        List<FilmRateInfoDTO> results = filmRateInfoMapper.toDTOs(objects.getContent());
        return new PageImpl<>(results, pageable, objects.getTotalElements());
    }

    public FilmRateInfoDTO rateFilm(FilmRateInfoDTO rateFilmDTO) {
        FilmDTO filmDTO = filmService.getOne(rateFilmDTO.getFilmId());
        filmService.update(filmDTO);
        long rate = (rateFilmDTO.getRate() != null && rateFilmDTO.getRate() > 1 && rateFilmDTO.getRate() <= 5) ? rateFilmDTO.getRate() : 1L;
        rateFilmDTO.setRateDate(LocalDateTime.now());
        rateFilmDTO.setReturned(false);
        rateFilmDTO.setRate((int) rate);
        rateFilmDTO.setReturnDate(LocalDateTime.now().plusDays(rate));
        rateFilmDTO.setCreatedWhen(LocalDateTime.now());
        rateFilmDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDTO(repository.save(mapper.toEntity(rateFilmDTO)));
    }

    public void returnFilm(final Long id) {
        FilmRateInfoDTO filmRateInfoDTO = getOne(id);
        filmRateInfoDTO.setReturned(true);
        filmRateInfoDTO.setReturnDate(LocalDateTime.now());
        FilmDTO filmDTO = filmRateInfoDTO.getFilmDTO();
        update(filmRateInfoDTO);
        filmService.update(filmDTO);
    }
//    public FilmRateInfoDTO changeRate(final Long id) {
//        FilmRateInfoDTO filmRateInfoDTO = getOne(id);
//        long rate = (int)filmRateInfoDTO.getRate();
//        filmRateInfoDTO.setReturned(true);
//        filmRateInfoDTO.setReturnDate(LocalDateTime.now());
//        FilmDTO filmDTO = filmRateInfoDTO.getFilmDTO();
//        update(filmRateInfoDTO);
//        filmService.update(filmDTO);
//        return mapper.toDTO(repository.save(mapper.toEntity(filmRateInfoDTO)));
//    }

    public void deleteHard(final Long id) {
        filmRateInfoRepository.deleteById(id);

    }
}


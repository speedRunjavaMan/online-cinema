package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmRateInfoDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.FilmRateInfo;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRepository;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.UserRepository;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.FilmService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Set;

@Component
public class FilmRateInfoMapper
        extends GenericMapper<FilmRateInfo, FilmRateInfoDTO> {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final FilmService filmService;
    protected FilmRateInfoMapper(ModelMapper mapper,
                                 FilmRepository filmRepository,
                                 UserRepository userRepository,
                                 FilmService filmService) {
        super(mapper, FilmRateInfo.class, FilmRateInfoDTO.class);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.filmService = filmService;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper.createTypeMap(FilmRateInfo.class, FilmRateInfoDTO.class)
                .addMappings(m -> m.skip(FilmRateInfoDTO::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmRateInfoDTO::setFilmId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmRateInfoDTO::setFilmDTO)).setPostConverter(toDtoConverter());

        super.modelMapper.createTypeMap(FilmRateInfoDTO.class, FilmRateInfo.class)
                .addMappings(m -> m.skip(FilmRateInfo::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(FilmRateInfo::setFilm)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmRateInfoDTO source, FilmRateInfo destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильма не найдено")));
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователя не найдено")));
    }

    @Override
    protected void mapSpecificFields(FilmRateInfo source, FilmRateInfoDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
        destination.setFilmDTO(filmService.getOne(source.getFilm().getId()));
    }

    @Override
    protected Set<Long> getIds(FilmRateInfo entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}


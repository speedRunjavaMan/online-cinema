package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.FilmRentInfo;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRepository;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.UserRepository;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.FilmService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Set;

@Component
public class FilmRentInfoMapper
        extends GenericMapper<FilmRentInfo, FilmRentInfoDTO> {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final FilmService filmService;
    protected FilmRentInfoMapper(ModelMapper mapper,
                                 FilmRepository filmRepository,
                                 UserRepository userRepository,
                                 FilmService filmService) {
        super(mapper, FilmRentInfo.class, FilmRentInfoDTO.class);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.filmService = filmService;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper.createTypeMap(FilmRentInfo.class, FilmRentInfoDTO.class)
                .addMappings(m -> m.skip(FilmRentInfoDTO::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmRentInfoDTO::setFilmId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmRentInfoDTO::setFilmDTO)).setPostConverter(toDtoConverter());

        super.modelMapper.createTypeMap(FilmRentInfoDTO.class, FilmRentInfo.class)
                .addMappings(m -> m.skip(FilmRentInfo::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(FilmRentInfo::setFilm)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmRentInfoDTO source, FilmRentInfo destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильма не найдено")));
        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователя не найдено")));
    }

    @Override
    protected void mapSpecificFields(FilmRentInfo source, FilmRentInfoDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
        destination.setFilmDTO(filmService.getOne(source.getFilm().getId()));
    }

    @Override
    protected Set<Long> getIds(FilmRentInfo entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }
}

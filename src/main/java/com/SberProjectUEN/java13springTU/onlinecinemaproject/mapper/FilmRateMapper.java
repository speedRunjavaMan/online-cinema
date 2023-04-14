//package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;
//
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmRateDTO;
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRepository;
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.UserRepository;
//import com.SberProjectUEN.java13springTU.onlinecinemaproject.service.FilmService;
//import jakarta.annotation.PostConstruct;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//import org.webjars.NotFoundException;
//
//import java.util.Set;
//
//@Component
//public class FilmRateMapper
//        extends GenericMapper<Film, FilmRateDTO> {
//    private final FilmRepository filmRepository;
//    private final UserRepository userRepository;
//
//    protected FilmRateMapper(ModelMapper mapper,
//                             FilmRepository filmRepository,
//                             UserRepository userRepository) {
//        super(mapper, Film.class, FilmRateDTO.class);
//        this.filmRepository = filmRepository;
//        this.userRepository = userRepository;
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        super.modelMapper.createTypeMap(Film.class, FilmRateDTO.class);
////                .addMappings(m -> m.skip(FilmRateDTO::setUserId)).setPostConverter(toDtoConverter())
////                .addMappings(m -> m.skip(FilmRateDTO::setFilmId)).setPostConverter(toDtoConverter())
////                .addMappings(m -> m.skip(FilmRateDTO::setFilmDTO)).setPostConverter(toDtoConverter());
//
//        super.modelMapper.createTypeMap(FilmRateDTO.class, Film.class);
////                .addMappings(m -> m.skip(Film::setUser)).setPostConverter(toEntityConverter())
////                .addMappings(m -> m.skip(Film::setFilm)).setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    protected void mapSpecificFields(FilmRateDTO source, Film destination) {
////        destination.setFilm(filmRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильма не найдено")));
////        destination.setUser(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователя не найдено")));
//    }
//
//    @Override
//    protected void mapSpecificFields(Film source, FilmRateDTO destination) {
////        destination.setUserId(source.getUser().getId());
////        destination.setFilmId(source.getFilm().getId());
////        destination.setFilmDTO(filmService.getOne(source.getFilm().getId()));
//    }
//
//    @Override
//    protected Set<Long> getIds(Film entity) {
//        throw new UnsupportedOperationException("Метод недоступен");
//    }
//}
//
//

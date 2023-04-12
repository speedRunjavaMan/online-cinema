package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;


import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.DirectorWithFilmsDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Director;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorWithFilmsMapper
        extends GenericMapper<Director, DirectorWithFilmsDTO> {

    private final FilmRepository filmRepository;

    protected DirectorWithFilmsMapper(ModelMapper mapper,
                                      FilmRepository filmRepository
    ) {
        super(mapper, Director.class, DirectorWithFilmsDTO.class);
        this.filmRepository = filmRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorWithFilmsDTO.class)
                .addMappings(m -> m.skip(DirectorWithFilmsDTO::setFilmsIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(DirectorWithFilmsDTO.class, Director.class)
                .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorWithFilmsDTO source, Director destination) {
        destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorWithFilmsDTO destination) {
        destination.setFilmsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Director entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}

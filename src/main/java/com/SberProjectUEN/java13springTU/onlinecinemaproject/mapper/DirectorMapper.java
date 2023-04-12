package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Director;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectorMapper
        extends GenericMapper<Director, DirectorDTO> {
    private final FilmRepository filmRepository;

    protected DirectorMapper(ModelMapper modelMapper,
                             FilmRepository filmRepository) {
        super(modelMapper, Director.class, DirectorDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Director.class, DirectorDTO.class)
                .addMappings(m -> m.skip(DirectorDTO::setFilmsIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(DirectorDTO.class, Director.class)
                .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(DirectorDTO source, Director destination) {
        if (!Objects.isNull(source.getFilmsIds())) {
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
        }
        else {
            destination.setFilms(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Director source, DirectorDTO destination) {
        destination.setFilmsIds(getIds(source));
    }

    protected Set<Long> getIds(Director director) {
        return Objects.isNull(director) || Objects.isNull(director.getFilms())
                ? null
                : director.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}

package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.ComposerDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Composer;
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
public class ComposerMapper
        extends GenericMapper<Composer, ComposerDTO> {
    private final FilmRepository filmRepository;

    protected ComposerMapper(ModelMapper modelMapper,
                             FilmRepository filmRepository) {
        super(modelMapper, Composer.class, ComposerDTO.class);
        this.filmRepository = filmRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Composer.class, ComposerDTO.class)
                .addMappings(m -> m.skip(ComposerDTO::setFilmsIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(ComposerDTO.class, Composer.class)
                .addMappings(m -> m.skip(Composer::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ComposerDTO source, Composer destination) {
        if (!Objects.isNull(source.getFilmsIds())) {
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
        }
        else {
            destination.setFilms(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Composer source, ComposerDTO destination) {
        destination.setFilmsIds(getIds(source));
    }

    protected Set<Long> getIds(Composer composer) {
        return Objects.isNull(composer) || Objects.isNull(composer.getFilms())
                ? null
                : composer.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}




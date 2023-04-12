package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;


import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.ComposerWithFilmsDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Composer;
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
public class ComposerWithFilmsMapper
        extends GenericMapper<Composer, ComposerWithFilmsDTO> {

    private final FilmRepository filmRepository;

    protected ComposerWithFilmsMapper(ModelMapper mapper,
                                      FilmRepository filmRepository
    ) {
        super(mapper, Composer.class, ComposerWithFilmsDTO.class);
        this.filmRepository = filmRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Composer.class, ComposerWithFilmsDTO.class)
                .addMappings(m -> m.skip(ComposerWithFilmsDTO::setFilmsIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(ComposerWithFilmsDTO.class, Composer.class)
                .addMappings(m -> m.skip(Composer::setFilms)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ComposerWithFilmsDTO source, Composer destination) {
        destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
    }

    @Override
    protected void mapSpecificFields(Composer source, ComposerWithFilmsDTO destination) {
        destination.setFilmsIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Composer entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}



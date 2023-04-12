package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmWithComposersDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.ComposerRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmWithComposersMapper
        extends GenericMapper<Film, FilmWithComposersDTO> {

    private final ComposerRepository composerRepository;

    protected FilmWithComposersMapper(ModelMapper mapper,
                                      ComposerRepository composerRepository
    ) {
        super(mapper, Film.class, FilmWithComposersDTO.class);
        this.composerRepository = composerRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmWithComposersDTO.class)
                .addMappings(m -> m.skip(FilmWithComposersDTO::setComposersIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmWithComposersDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setComposers)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithComposersDTO source, Film destination) {
        destination.setComposers(new HashSet<>(composerRepository.findAllById(source.getComposersIds())));
    }

    @Override
    protected void mapSpecificFields(Film source, FilmWithComposersDTO destination) {
        destination.setComposersIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Film entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getComposers().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}



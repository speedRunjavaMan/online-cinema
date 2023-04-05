package com.SberProjectUEN.java13springTU.libraryproject.mapper;

import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmWithDirectorsDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.libraryproject.repository.ComposerRepository;
import com.SberProjectUEN.java13springTU.libraryproject.repository.DirectorRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmWithDirectorsMapper
        extends GenericMapper<Film, FilmWithDirectorsDTO> {

    private final DirectorRepository directorRepository;
    private final ComposerRepository composerRepository;

    protected FilmWithDirectorsMapper(ModelMapper mapper,
                                      DirectorRepository directorRepository,
                                      ComposerRepository composerRepository
    ) {
        super(mapper, Film.class, FilmWithDirectorsDTO.class);
        this.directorRepository = directorRepository;
        this.composerRepository = composerRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmWithDirectorsDTO.class)
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setDirectorsIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setComposersIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmWithDirectorsDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Film::setComposers)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithDirectorsDTO source, Film destination) {
        destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
        destination.setComposers(new HashSet<>(composerRepository.findAllById(source.getComposersIds())));
    }

    @Override
    protected void mapSpecificFields(Film source, FilmWithDirectorsDTO destination) {
        destination.setDirectorsIds(getIds(source));
        destination.setComposersIds(getIds(source));
    }

    @Override
    protected Set<Long> getIds(Film entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getId())
                ? null
                : entity.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}

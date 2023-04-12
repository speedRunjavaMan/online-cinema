package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.FilmWithDirectorsDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Film;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.DirectorRepository;
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

    protected FilmWithDirectorsMapper(ModelMapper mapper,
                                      DirectorRepository directorRepository
    ) {
        super(mapper, Film.class, FilmWithDirectorsDTO.class);
        this.directorRepository = directorRepository;
    }

    @Override
    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmWithDirectorsDTO.class)
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setDirectorsIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmWithDirectorsDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithDirectorsDTO source, Film destination) {
        destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
    }

    @Override
    protected void mapSpecificFields(Film source, FilmWithDirectorsDTO destination) {
        destination.setDirectorsIds(getIds(source));
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

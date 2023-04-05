package com.SberProjectUEN.java13springTU.libraryproject.mapper;

import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.libraryproject.repository.ComposerRepository;
import com.SberProjectUEN.java13springTU.libraryproject.repository.DirectorRepository;
import com.SberProjectUEN.java13springTU.libraryproject.utils.DateFormatter;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper
      extends GenericMapper<Film, FilmDTO> {
    private final DirectorRepository directorRepository;
    private final ComposerRepository composerRepository;
    protected FilmMapper(ModelMapper mapper, DirectorRepository directorRepository, ComposerRepository composerRepository) {
        super(mapper, Film.class, FilmDTO.class);
        this.directorRepository = directorRepository;
        this.composerRepository = composerRepository;
    }
    
    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
              .addMappings(m -> m.skip(FilmDTO::setDirectorsIds)).setPostConverter(toDtoConverter())
              .addMappings(m -> m.skip(FilmDTO::setComposersIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(FilmDTO.class, Film.class)
              .addMappings(m -> m.skip(Film::setDirectors)).setPostConverter(toEntityConverter())
              .addMappings(m -> m.skip(Film::setComposers)).setPostConverter(toEntityConverter())
              .addMappings(m -> m.skip(Film::setPremierYear)).setPostConverter(toEntityConverter());
    }
    
    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if (!Objects.isNull(source.getDirectorsIds())) {
            destination.setDirectors(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
        }
        else {
            destination.setDirectors(Collections.emptySet());
        }
        if (!Objects.isNull(source.getComposersIds())) {
            destination.setComposers(new HashSet<>(composerRepository.findAllById(source.getComposersIds())));
        }
        else {
            destination.setDirectors(Collections.emptySet());
        }
        destination.setPremierYear(DateFormatter.formatStringToDate(source.getPremierYear()));
    }
    
    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {

        destination.setDirectorsIds(getIds(source));
        destination.setComposersIds(getIds(source));
    }
    
    protected Set<Long> getIds(Film film) {
        return Objects.isNull(film) || Objects.isNull(film.getDirectors())
               ? null
               : film.getDirectors().stream()
                     .map(GenericModel::getId)
                     .collect(Collectors.toSet());
    }
}

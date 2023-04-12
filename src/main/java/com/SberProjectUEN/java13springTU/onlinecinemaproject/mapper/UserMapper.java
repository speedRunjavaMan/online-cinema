package com.SberProjectUEN.java13springTU.onlinecinemaproject.mapper;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.GenericModel;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.User;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.repository.FilmRentInfoRepository;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.utils.DateFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper
        extends GenericMapper<User, UserDTO> {

    private FilmRentInfoRepository filmRentInfoRepository;

    protected UserMapper(ModelMapper modelMapper,
                         FilmRentInfoRepository filmRentInfoRepository) {
        super(modelMapper, User.class, UserDTO.class);
        this.filmRentInfoRepository = filmRentInfoRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setUserFilmsRent)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setFilmRentInfos)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getUserFilmsRent())) {
            destination.setFilmRentInfos(new HashSet<>(filmRentInfoRepository.findAllById(source.getUserFilmsRent())));
        }
        else {
            destination.setFilmRentInfos(Collections.emptySet());
        }
        destination.setBirthDate(DateFormatter.formatStringToDate(source.getBirthDate()));
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {

        destination.setUserFilmsRent(getIds(source));
    }

    @Override
    protected Set<Long> getIds(User entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getFilmRentInfos())
                ? null
                : entity.getFilmRentInfos().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}

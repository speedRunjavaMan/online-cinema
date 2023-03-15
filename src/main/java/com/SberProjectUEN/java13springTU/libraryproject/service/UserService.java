package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.FilmRentInfoDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.RoleDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.UserMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Film;
import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import com.SberProjectUEN.java13springTU.libraryproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService
        extends GenericService<User, UserDTO> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected UserService(UserRepository userRepository,
                          UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository, userMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDTO getUserByLogin(final String login) {
        return mapper.toDto(((UserRepository) repository).findUserByLogin(login));
    }

    public UserDTO getUserByEmail(final String email) {
        return mapper.toDto(((UserRepository) repository).findUserByEmail(email));
    }

    public Boolean checkPassword(String password, UserDetails userDetails) {
        return bCryptPasswordEncoder.matches(password, userDetails.getPassword());
    }


    @Override
    public UserDTO create(UserDTO object) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        object.setRole(roleDTO);
        return mapper.toDto(repository.save(mapper.toEntity(object)));
    }
//    private Set<Long> userFilmsRent;
/*    public List<FilmRentInfoDTO> getAllUserFilmsRent(Long userId) {
        UserDTO object = this.getOne(userId);
        Set<Long> user = object.getUserFilmsRent();
        object.setUserFilmsRent(user.stream().map(FilmRentInfoDTO::getId).collect(Collectors.toCollection()));
        return mapper.toDTOs(repository.save(mapper.toEntity(object)));
    }*/


//    protected Set<Long> getIds(Film entity) {
//        return Objects.isNull(entity) || Objects.isNull(entity.getId())
//                ? null
//                : entity.getDirectors().stream()
//                .map(GenericModel::getId)
//                .collect(Collectors.toSet());
    }




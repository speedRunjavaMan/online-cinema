package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.RoleDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.UserMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import com.SberProjectUEN.java13springTU.libraryproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
        extends GenericService<User, UserDTO> {

    protected UserService(UserRepository userRepository,
                          UserMapper userMapper) {
        super(userRepository, userMapper);
    }

    @Override
    public UserDTO create(UserDTO object) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        object.setRole(roleDTO);
        return mapper.toDto(repository.save(mapper.toEntity(object)));
    }
}


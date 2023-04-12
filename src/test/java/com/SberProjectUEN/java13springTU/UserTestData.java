package com.SberProjectUEN.java13springTU;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.RoleDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Role;
import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public interface UserTestData {

    UserDTO USER_DTO = new UserDTO(
            "login",
            "password",
            "email",
            "birthDate",
            "firstName",
            "lastName",
            "middleName",
            "phone",
            "address",
            new RoleDTO(),
            "changePasswordToken",
            new HashSet<>(),
            false
    );

    List<UserDTO> USER_DTO_LIST = List.of(USER_DTO);

    User USER = new User(
            "login",
            "password",
            "firstName",
            "lastName",
            "middleName",
            LocalDate.now(),
            "phone",
            "address",
            "email",
            LocalDateTime.now(),
            "changePasswordToken",
            new Role(),
            new HashSet<>()
    );

    List<User> USER_LIST = List.of(USER);
}
//

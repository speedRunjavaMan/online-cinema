package com.SberProjectUEN.java13springTU.libraryproject.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO
      extends GenericDTO {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String phone;
    private String address;
    private String email;
    private RoleDTO role;
    private String changePasswordToken;
    private Set<Long> userFilmsRent;
    private boolean isDeleted;
}

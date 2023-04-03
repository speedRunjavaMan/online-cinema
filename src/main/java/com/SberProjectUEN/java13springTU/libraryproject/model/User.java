package com.SberProjectUEN.java13springTU.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueEmail", columnNames = "email"),
                @UniqueConstraint(name = "uniqueLogin", columnNames = "login")})

@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "users_seq", allocationSize = 1, initialValue = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class User
        extends GenericModel {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "createdWhen", nullable = false)
    private LocalDateTime createdWhen;

    @Column(name = "change_password_token")
    private String changePasswordToken;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_ROLES"))
    private Role role;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<FilmRentInfo> filmRentInfos;
}

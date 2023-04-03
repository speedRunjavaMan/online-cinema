package com.SberProjectUEN.java13springTU.libraryproject.REST.controller;

import com.SberProjectUEN.java13springTU.libraryproject.config.jwt.JWTTokenUtil;
import com.SberProjectUEN.java13springTU.libraryproject.dto.LoginDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import com.SberProjectUEN.java13springTU.libraryproject.service.UserService;
import com.SberProjectUEN.java13springTU.libraryproject.service.userdetails.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями онлайн кинотеатра")
@Slf4j
public class UserController
        extends GenericController<User, UserDTO> {
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTTokenUtil jwtTokenUtil;
    private final UserService userService;

    public UserController(UserService userService,
                          CustomUserDetailsService customUserDetailsService,
                          JWTTokenUtil jwtTokenUtil) {
        super(userService);
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        log.info("LoginDTO: {}", loginDTO);
        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDTO.getLogin());
        log.info("foundUser, {}", foundUser);
        if (!userService.checkPassword(loginDTO.getPassword(), foundUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка авторизации!\nНеверный пароль");
        }
        String token = jwtTokenUtil.generateToken(foundUser);
        response.put("token", token);
        response.put("username", foundUser.getUsername());
        response.put("authorities", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);
    }
}


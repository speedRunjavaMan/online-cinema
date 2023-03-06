package com.SberProjectUEN.java13springTU.libraryproject.controller;

import com.SberProjectUEN.java13springTU.libraryproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import com.SberProjectUEN.java13springTU.libraryproject.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи",
        description = "Контроллер для работы с пользователями онлайн кинотеатра")
public class UserController
        extends GenericController<User, UserDTO> {

    public UserController(UserService userService) {
        super(userService);
    }
}


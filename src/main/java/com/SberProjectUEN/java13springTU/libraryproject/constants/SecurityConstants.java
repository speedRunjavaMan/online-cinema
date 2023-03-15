package com.SberProjectUEN.java13springTU.libraryproject.constants;

import java.util.List;

public interface SecurityConstants {

    List<String> RESOURCES_WHITE_LIST = List.of("/resources/**",
            "/js/**",
            "/css/**",
            "/",
            // -- Swagger UI v3 (OpenAPI)
            "/swagger-ui/**",
            "/v3/api-docs/**");

    List<String> BOOKS_WHITE_LIST = List.of("/books");
    List<String> BOOKS_PERMISSION_LIST = List.of("/books/add",
            "/books/update",
            "/books/delete");

    List<String> USERS_WHITE_LIST = List.of("/login",
            "/users/registration",
            "/users/remember-password",
            "/users/change-password");

    List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}


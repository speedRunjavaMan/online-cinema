package com.SberProjectUEN.java13springTU.libraryproject.constants;

public interface Errors {
    class Films {
        public static final String FILM_DELETE_ERROR = "Фильм не может быть удален, так как у него есть активные аренды";
    }

    class Directors {
        public static final String DIRECTOR_DELETE_ERROR = "Режиссер не может быть удален, так как у его фильмов есть активные аренды";
    }
    class Composers {
        public static final String COMPOSER_DELETE_ERROR = "Композитор не может быть удален, так как у его фильмов есть активные аренды";
    }
    class Users {
        public static final String USER_FORBIDDEN_ERROR = "У вас нет прав просматривать информацию о пользователе";
    }

    class REST {
        public static final String DELETE_ERROR_MESSAGE = "Удаление невозможно";
        public static final String AUTH_ERROR_MESSAGE = "Неавторизованный пользователь";
        public static final String ACCESS_ERROR_MESSAGE = "Отказано в доступе!";
    }
}


package com.SberProjectUEN.java13springTU.libraryproject.repository;

import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
      extends GenericRepository<User> {
    //select * from users where login = ?
//    @Query(nativeQuery = true, value = "select * from users where login = :login")
    User findUserByLogin(String login);

    User findUserByEmail(String email);
}


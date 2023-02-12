package com.SberProjectUEN.java13springTU.dbexample.dao;

import com.SberProjectUEN.java13springTU.dbexample.model.Book;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookDaoBean {
    
    private final Connection connection;
    
    public BookDaoBean(Connection connection) {
        this.connection = connection;
    }
    
    public Book findBookById(Integer filmId) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement("select * from films where id = ?");
        selectQuery.setInt(1, filmId);
        ResultSet resultSet = selectQuery.executeQuery();
        Book film = new Book();
        while (resultSet.next()) {
            film.setFilmAuthor(resultSet.getString("author"));
            film.setFilmTitle(resultSet.getString("title"));
            film.setDateAdded(resultSet.getDate("date_added"));
            System.out.println(film);
        }
        return film;
    }
    
}

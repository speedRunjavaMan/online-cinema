package com.SberProjectUEN.java13springTU.dbexample.dao;

import com.SberProjectUEN.java13springTU.dbexample.DB.DBApp;
import com.SberProjectUEN.java13springTU.dbexample.model.Book;

import java.sql.*;

public class BookDaoJDBC {
    //select * from book where id = ?
    //JDBC - Java DataBase Connection
    public Book findBookById(Integer filmId) {
        try (Connection connection = DBApp.INSTANCE.newConnection()) {
            if (connection != null) {
                System.out.println("ура! мы подключились к БД!!!!");
            }
            else {
                System.out.println("база данных отдыхает, не трогайте!");
            }
            PreparedStatement selectQuery = connection.prepareStatement("select * from films where id = ?");
            selectQuery.setInt(1, filmId);
            ResultSet resultSet = selectQuery.executeQuery();
            while (resultSet.next()) {
                Book film = new Book();
                film.setFilmAuthor(resultSet.getString("author"));
                film.setFilmTitle(resultSet.getString("title"));
                film.setDateAdded(resultSet.getDate("date_added"));
                System.out.println(film);
                return film;
            }
        }
        catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return null;
    }
    
    //public List<Books> findBooksByTitle(String title){}
    
//    public Connection newConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/local_db",
//                                           "postgres", "12345");
//    }
}

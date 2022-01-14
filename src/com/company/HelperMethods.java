package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelperMethods {
    public static Movie getMovie (String title) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String getSql = "SELECT * FROM movies WHERE title='"+title+"';";
        ResultSet rs = stmt.executeQuery(getSql);

        int id = rs.getInt("id");
        title = rs.getString("title");
        int year  = rs.getInt("year");
        String  description = rs.getString("description");
        boolean isRented = rs.getBoolean("isRented");
        Movie movie = new Movie(id, title, year, description);
        return movie;
    }

    public static List<Movie> getAllNamesLike (String title) throws ClassNotFoundException, SQLException {
        List<Movie> allNamesLike = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String getSql = "SELECT * FROM movies WHERE title LIKE '%"+title+"%';";
        ResultSet rs = stmt.executeQuery(getSql);

        while (rs.next()){
            int id = rs.getInt("id");
            title = rs.getString("title");
            int year  = rs.getInt("year");
            String  description = rs.getString("description");
            boolean isRented = rs.getBoolean("isRented");
            Movie movie = new Movie(id, title, year, description);
            allNamesLike.add(movie);
        }
        return allNamesLike;
    }

    public static Movie insert (Movie movie) throws SQLException,  ClassNotFoundException  {
        int id = movie.getIndex();
        String title = movie.getTitle().replaceAll("_!_", " ");
        System.out.println(title);
        int year = movie.getYear();
        String description = movie.getDescription();
        String genre = movie.getGenre();
        boolean isRented = movie.isRented();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();

        String sqlQuery = "CREATE TABLE IF NOT EXISTS movies " +
                "(id INT PRIMARY KEY  NOT NULL UNIQUE," +
                " title TEXT NOT NULL UNIQUE, " +
                " year  INT     NOT NULL, " +
                " description        STRING, " +
                " genre TEXT NOT NULL," +
                " isRented BOOLEAN NOT NULL);";
        stmt.execute(sqlQuery);
        String insertSql = "INSERT INTO movies (id,title,year,description,genre,isRented) " +
                "VALUES ("+id+", '"+ title +"', "+ year +", '"+ description +"', '"+ genre +"', " +isRented+ " );";
        stmt.execute(insertSql);
        stmt.close();
        connection.close();
        return movie;
    }

    public static List<Movie> getAll() throws ClassNotFoundException, SQLException {
        List<Movie> currentMovies = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String getSql = "SELECT * FROM movies;";
        ResultSet rs = stmt.executeQuery(getSql);

        while (rs.next()){
            int id = rs.getInt("id");
            String  title = rs.getString("title");
            int year  = rs.getInt("year");
            String  description = rs.getString("description");
            boolean isRented = rs.getBoolean("isRented");
            Movie movie = new Movie(id, title, year, description);
            currentMovies.add(movie);
        }


        return currentMovies;
    }
}


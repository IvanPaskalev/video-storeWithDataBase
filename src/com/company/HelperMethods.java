package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public static String deleteAllMovies () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "DELETE FROM movies;";
        stmt.executeUpdate(updateSql);
        String update2Sql = "VACUUM";
        stmt.executeUpdate(update2Sql);
        String allMoviesDeleted = "All movies are deleted!\nData Base is empty.";
        stmt.close();
        connection.close();
        return allMoviesDeleted;
    }

    public static String deleteOneMovie(Movie movie) throws ClassNotFoundException, SQLException {
        String deleteOneMovie = movie.getTitle();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "DELETE FROM movies WHERE title='"+deleteOneMovie+"';";
        stmt.executeUpdate(updateSql);
        deleteOneMovie = "Movie deleted!";
        stmt.close();
        connection.close();
        return deleteOneMovie;
    }

    public static Movie editIsRentedFalse (Movie movie) throws ClassNotFoundException, SQLException {
        int idToCorrect = movie.getIndex();
        boolean isRentedNew = false;
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "UPDATE movies SET isRented = "+isRentedNew+" WHERE id="+idToCorrect+";";
        stmt.executeUpdate(updateSql);
        String selectSql = "SELECT * FROM movies WHERE id= "+idToCorrect+";";
        ResultSet rs = stmt.executeQuery(selectSql);
        movie.setRented(rs.getBoolean("isRented"));
        stmt.close();
        connection.close();
        return movie;
    }

    public static Movie editIsRentedTrue (Movie movie) throws ClassNotFoundException, SQLException {
        int idToCorrect = movie.getIndex();
        boolean isRentedNew = true;
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "UPDATE movies SET isRented = "+isRentedNew+" WHERE id="+idToCorrect+";";
        stmt.executeUpdate(updateSql);
        String selectSql = "SELECT * FROM movies WHERE id= "+idToCorrect+";";
        ResultSet rs = stmt.executeQuery(selectSql);
        movie.setRented(rs.getBoolean("isRented"));
        stmt.close();
        connection.close();
        return movie;
    }

    public static Movie editDescription (Movie movie) throws ClassNotFoundException, SQLException {
        int idToCorrect = movie.getIndex();
        String descriptionNew = movie.getDescription();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "UPDATE movies SET description= '"+descriptionNew+"'  WHERE id="+idToCorrect+";";
        stmt.executeUpdate(updateSql);
        String selectSql = "SELECT * FROM movies WHERE id= "+idToCorrect+";";
        ResultSet rs = stmt.executeQuery(selectSql);
        movie.setDescription(rs.getString("description"));
        stmt.close();
        connection.close();
        return movie;
    }

    public static Movie editGenre (Movie movie) throws ClassNotFoundException, SQLException {
        int idToCorrect = movie.getIndex();
        String genreNew = movie.getGenre();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "UPDATE movies SET genre= '"+genreNew+"'  WHERE id="+idToCorrect+";";
        stmt.executeUpdate(updateSql);
        String selectSql = "SELECT * FROM movies WHERE id= "+idToCorrect+";";
        ResultSet rs = stmt.executeQuery(selectSql);;
        movie.setGenre(rs.getString("genre"));
        stmt.close();
        connection.close();
        return movie;
    }

    public static Movie editYear (Movie movie) throws ClassNotFoundException, SQLException {
        int idToCorrect = movie.getIndex();
        int yearNew = movie.getYear();
        String titleNew = movie.getTitle();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "UPDATE movies SET year= "+yearNew+"  WHERE id="+idToCorrect+";";
        stmt.executeUpdate(updateSql);
        String selectSql = "SELECT * FROM movies WHERE id= "+idToCorrect+";";
        ResultSet rs = stmt.executeQuery(selectSql);;
        movie.setYear(rs.getInt("year"));
        stmt.close();
        connection.close();
        return movie;
    }

    public static Movie editTitle (Movie movie) throws ClassNotFoundException, SQLException {
        int idToCorrect = movie.getIndex();
        String titleNew = movie.getTitle();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String updateSql = "UPDATE movies SET title= '"+titleNew+"'  WHERE id="+idToCorrect+";";
        stmt.executeUpdate(updateSql);
        String selectSql = "SELECT * FROM movies WHERE title= '"+titleNew+"';";
        ResultSet rs = stmt.executeQuery(selectSql);;
        movie.setTitle(rs.getString("title"));
        stmt.close();
        connection.close();
        return movie;
    }

    public static List<Movie> getAllGenre (String genre) throws ClassNotFoundException, SQLException {
        List<Movie> getAllGenre = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String getSql = "SELECT * FROM movies WHERE genre LIKE '%"+genre+"%';";
        ResultSet rs = stmt.executeQuery(getSql);

        while (rs.next()){
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int year  = rs.getInt("year");
            String  description = rs.getString("description");
            genre = rs.getString("genre");
            boolean isRented = rs.getBoolean("isRented");
            Movie movie = new Movie(id, title, year, description, genre);
            getAllGenre.add(movie);
        }
        stmt.close();
        connection.close();
        return getAllGenre;
    }

    public static List<Movie> getAllYear (int year) throws ClassNotFoundException, SQLException {
        List<Movie> getAllYear = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement stmt = connection.createStatement();
        String getSql = "SELECT * FROM movies WHERE year LIKE "+year+";";
        ResultSet rs = stmt.executeQuery(getSql);

        while (rs.next()){
            int id = rs.getInt("id");
            String title = rs.getString("title");
            year  = rs.getInt("year");
            String  description = rs.getString("description");
            String genre = rs.getString("genre");
            boolean isRented = rs.getBoolean("isRented");
            Movie movie = new Movie(id, title, year, description, genre);
            getAllYear.add(movie);
        }
        stmt.close();
        connection.close();
        return getAllYear;
    }

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
        String genre = rs.getString("genre");
        boolean isRented = rs.getBoolean("isRented");
        Movie movie = new Movie(id, title, year, description, genre);
        stmt.close();
        connection.close();
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
            String genre = rs.getString("genre");
            boolean isRented = rs.getBoolean("isRented");
            Movie movie = new Movie(id, title, year, description, genre);
            allNamesLike.add(movie);
        }
        stmt.close();
        connection.close();
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

    public static List<Movie> getAllMovies() throws ClassNotFoundException, SQLException {
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
            String genre = rs.getString("genre");
            boolean isRented = rs.getBoolean("isRented");
            Movie movie = new Movie(id, title, year, description, genre);
            currentMovies.add(movie);
        }
        stmt.close();
        connection.close();


        return currentMovies;
    }
}



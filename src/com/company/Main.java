package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Hi!\n1 - Save new movie\n2 - search\n3 - Edit\n4 - See all movies\n5 - Delete\n6 - Exit");
            boolean isQuiting = false;
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    int id = 0;
                    List<Movie> currentMovies = null;
                    try {
                        currentMovies = HelperMethods.getAllMovies();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    id = currentMovies.size();
                    Movie movieNew = createMovieObject();
                    movieNew.setIndex(id);
                    System.out.println(movieNew);
                    try {
                        HelperMethods.insert(movieNew);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;


                case 2:
                    System.out.println("If you want to search title press 1\nIf you want to search year press 2\nIf you want to search genre press 3");
                    switch (new Scanner(System.in).nextInt()) {
                        case 1:
                            System.out.println("1 - Search one movie\n2 - Search all related names");
                            switch (new Scanner(System.in).nextInt()){
                                case 1:
                                    System.out.println("Enter movie title and press Enter");
                                    String title1 = new Scanner(System.in).nextLine();
                                    try {
                                        Movie movie1 = HelperMethods.getMovie(title1);
                                        System.out.println(movie1);
                                        break;
                                    } catch (ClassNotFoundException | SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case 2:
                                    System.out.println("Enter movie title and press Enter");
                                    String title2 = new Scanner(System.in).nextLine();
                                    try {
                                        List<Movie> resultAllNamesLike = HelperMethods.getAllNamesLike(title2);
                                        for (Movie movie : resultAllNamesLike) {
                                            System.out.println(movie);
                                        }
                                    } catch (ClassNotFoundException | SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                            }
                            break;
                        case 2:
                            System.out.println("Enter year and press Enter");
                            int year = new Scanner(System.in).nextInt();
                            try {
                                List<Movie> getAllYear = HelperMethods.getAllYear(year);
                                for (Movie movie : getAllYear) {
                                    System.out.println(movie);
                                }
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Enter genre");
                            String genre = new Scanner(System.in).nextLine();
                            try {
                                List<Movie> getAllGenre = HelperMethods.getAllGenre(genre);
                                for (Movie movie : getAllGenre) {
                                    System.out.println(movie);
                                }
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Enter Title");
                    String title = new Scanner(System.in).nextLine();
                    try {
                        Movie movieToCorrect = HelperMethods.getMovie(title);
                        System.out.println("------------------------");
                        System.out.println("Result:");
                        System.out.println(movieToCorrect);
                        System.out.println("------------------------");
                        System.out.println("1 - edit title\n2 - edit year\n3 - edit genre\n4 - add description\n5 - edit \"isRented\"");
                        switch (new Scanner(System.in).nextInt()){
                            case 1://edit title
                                System.out.println("Current title:");
                                System.out.println(movieToCorrect.getTitle());
                                System.out.println("Enter new title and press Enter");
                                try {
                                    movieToCorrect.setTitle(new Scanner(System.in).nextLine());
                                    Movie movieCorrectedTitle = HelperMethods.editTitle(movieToCorrect);
                                    System.out.println("-----------------------");
                                    System.out.println("Result:");
                                    System.out.println(movieCorrectedTitle);
                                    System.out.println("-----------------------");
                                } catch (ClassNotFoundException | SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2://edit year
                                System.out.println("Current year:");
                                System.out.println(movieToCorrect.getYear());
                                System.out.println("Enter new year and press Enter");
                                try {
                                    movieToCorrect.setYear(new Scanner(System.in).nextInt());
                                    Movie movieCorrectedYear = HelperMethods.editYear(movieToCorrect);
                                    System.out.println("-----------------------");
                                    System.out.println("Result:");
                                    System.out.println(movieCorrectedYear);
                                    System.out.println("-----------------------");
                                } catch (ClassNotFoundException | SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3://edit genre
                                System.out.println("Current genre:");
                                System.out.println(movieToCorrect.getGenre());
                                System.out.println("Enter new genre and press Enter");
                                try {
                                    movieToCorrect.setGenre(new Scanner(System.in).nextLine());
                                    Movie movieCorrectedGenre = HelperMethods.editGenre(movieToCorrect);
                                    System.out.println("-----------------------");
                                    System.out.println("Result:");
                                    System.out.println(movieCorrectedGenre);
                                    System.out.println("-----------------------");
                                } catch (ClassNotFoundException | SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4://edit description
                                System.out.println("Current description:");
                                System.out.println(movieToCorrect.getDescription());
                                System.out.println("Enter new description and press Enter");
                                try {
                                    movieToCorrect.setDescription(new Scanner(System.in).nextLine());
                                    Movie movieCorrectedDescription = HelperMethods.editDescription(movieToCorrect);
                                    System.out.println("-----------------------");
                                    System.out.println("Result:");
                                    System.out.println(movieCorrectedDescription);
                                    System.out.println("-----------------------");
                                } catch (ClassNotFoundException | SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5://edit isRented - must go again, Why boolean in database is 1?
                                System.out.println("Current \"isRented\" = "+movieToCorrect.isRented()+"");
                                System.out.println("1 - Change status \"isRented\" to \"true\"");
                                System.out.println("2 - Change status \"isRented\" to \"false\"");
                                switch (new Scanner(System.in).nextInt()){
                                    case 1:
                                        try {
                                            Movie movieIsRented = HelperMethods.editIsRentedTrue(movieToCorrect);
                                            System.out.println("-----------------------");
                                            System.out.println("Result:");
                                            System.out.println(movieIsRented);
                                            System.out.println("-----------------------");
                                        } catch (ClassNotFoundException | SQLException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            Movie movieIsNotRented = HelperMethods.editIsRentedFalse(movieToCorrect);
                                            System.out.println("-----------------------");
                                            System.out.println("Result:");
                                            System.out.println(movieIsNotRented);
                                            System.out.println("-----------------------");
                                        } catch (ClassNotFoundException | SQLException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                }
                                break;
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }

                    //----------------------------------------------
                    break;

                case 4:
                    try {
                        List<Movie> allMovies = HelperMethods.getAllMovies();
                        System.out.println("Results:");
                        for (Movie movie : allMovies) {
                            System.out.println(movie);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5://delete
                    System.out.println("1 - Delete one movie\n2 - delete all movies");
                    switch (new Scanner(System.in).nextInt()){
                        case 1:
                            System.out.println("Enter movie title");
                            String titleToDelete = new Scanner(System.in).nextLine();
                            try {
                                Movie movieToDelete = HelperMethods.getMovie(titleToDelete);
                                System.out.println("Result:");
                                System.out.println(movieToDelete);
                                System.out.println("Do you want to delete the movie?");
                                System.out.println("1 - Delete movie\n2 - Cancel");
                                switch (new Scanner(System.in).nextInt()){
                                    case 1:
                                        try {
                                            String movieDeleted = HelperMethods.deleteOneMovie(movieToDelete);
                                            System.out.println(movieDeleted);
                                        } catch (ClassNotFoundException | SQLException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 2:
                                        break;
                                }
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("1 - Delete all movies\n2 - Cancel");
                            switch (new Scanner(System.in).nextInt()){
                                case 1:
                                    String allMoviesDeleted = null;
                                    try {
                                        allMoviesDeleted = HelperMethods.deleteAllMovies();
                                        System.out.println(allMoviesDeleted);
                                    } catch (ClassNotFoundException | SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    break;
                            }
                            break;
                    }
                    break;
                case 6:
                    isQuiting = true;
                    break;
            }
            if (isQuiting) {
                break;
            }
        }
    }

    private static Movie createMovieObject() {
        System.out.println("enter movie title");
        Movie movie = new Movie();
        while (true) {
            String title = new Scanner(System.in).nextLine().replaceAll(" ", "_!_");
            if (!title.isEmpty()) {
                movie.setTitle(title);
                break;
            } else {
                System.out.println("No input entered");
            }


        }
        System.out.println("Enter year");
        while (true) {
            int year = new Scanner(System.in).nextInt();
            if (year != 0) {
                movie.setYear(year);
                break;
            } else {
                System.out.println("No input entered");
            }
        }
        System.out.println("enter genre");
        while (true) {
            String genre = new Scanner(System.in).nextLine();
            if (!genre.isEmpty()) {
                movie.setGenre(genre);
                break;
            } else {
                System.out.println("No input entered");
            }
        }
        while (true) {
            movie.setRented(false);
            break;
        }
        return movie;
    }


}
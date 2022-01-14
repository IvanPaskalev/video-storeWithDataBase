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
            System.out.println("Hi!\n1 - Save new movie\n2 - search\n3 - Edit\n4 - See all movies\n5 - Exit");
            boolean isQuiting = false;
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    int id = 0;
                    List<Movie> currentMovies = null;
                    try {
                        currentMovies = HelperMethods.getAll();
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
                                        for (int i = 0; i < resultAllNamesLike.size(); i++) {
                                            System.out.println(resultAllNamesLike.get(i));
                                            break;
                                        }
                                    } catch (ClassNotFoundException | SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                            }
                            break;
                        case 2:
                            System.out.println("Enter year");
                            int year = new Scanner(System.in).nextInt();
                            //---------------------------
                            break;
                        case 3:
                            System.out.println("Enter genre");
                            String genre = new Scanner(System.in).nextLine();
                            //----------------------------------
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Enter Title");
                    String title = new Scanner(System.in).nextLine();
                    //----------------------------------------------
                    break;

                case 4:
                    List<Movie> allMovies = getAllMovies();
                    for (Movie movie1: allMovies) {
                        System.out.println(movie1);
                    }
                    break;
                case 5:
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


    private static List<Movie> getAllMovies() {
        List<String> lines;
        List<Movie> movies = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("dataBase.txt"));
            for (String line : lines) {
                String[] info = line.split(" ");
                movies.add(new Movie(Integer.parseInt(info[0]), info[1].replaceAll("_!_", " "), Integer.parseInt(info[2].trim()), info[3]));
            }
        } catch (IOException e) {
        }
        return movies;
    }

}
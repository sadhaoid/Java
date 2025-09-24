package org.simplerental;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieService {
    private static List<Movie> movies = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("=====Movie Information Operation System=====");
            System.out.println("1. Upload");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Block");
            System.out.println("5. show");
            String command = scanner.next();
            switch(command) {
                case "1":
                    //
                    addMovie();
                    break;
                case "2":
                    //
                    deleteMovie();
                    break;
                case "3":
                    //
                    queryMovie();
                    break;
                case "4":
                    //
                    deleteStar();
                    break;
                case "5":
                    //
                    show();
                    break;
                case "Exit":
                    //
                    return;
                default:
                    System.out.println("Error Input");
            }
        }

    }

    private void show() {
        for(Movie m : movies){
            System.out.println(m);
        }
    }

    private void deleteStar() {
        System.out.println("=====Delete Star====");
        System.out.println("name");
        String star = scanner.next();

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getActor().contains(star)){
                movies.remove(movie);
                System.out.println("Success");
                i--;
            }

        }
        show();
    }

    private void deleteMovie() {
        System.out.println("=====delete=====");
        System.out.println("Input Delete Name");
        String name = scanner.next();

        System.out.println(deleteMovieByName(name));
    }

    private List<Movie> deleteMovieByName(String name) {
        for (Movie movie: movies) {
            if (movie.getName().equals(name)){
                movies.remove(movie);
                return movies;
            }
        }
        return null;
    }

    private void queryMovie() {
        System.out.println("=====query=====");
        System.out.println("Input query name");
        String name = scanner.next();
        Movie movie = queryMovieByName(name);
        if(movie != null){
            System.out.println(movie);
        }else{
            System.out.println("No Such Movie");
        }
    }

    public Movie queryMovieByName(String name) {
        for (Movie movie: movies) {
            if (movie.getName().equals(name)){
                return movie;
            }
        }
        return null;
    }

    private void addMovie() {
        System.out.println("=====add=====");
        Movie movie = new Movie();
        movie.setName(scanner.next());
        movie.setScore(scanner.nextDouble());
        movie.setActor(scanner.next());
        movie.setPrice(scanner.nextDouble());

        movies.add(movie);
    }
}

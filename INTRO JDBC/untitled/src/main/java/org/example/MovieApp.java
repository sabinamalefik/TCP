package org.example;

public class MovieApp {

    public static void main(String[] args) {

        MovieRepository repository = new MovieRepository();

        repository.createTable();

        System.out.println("\n=== CREATE ===");

        repository.createMovie(new Movie("Inception", "Sci-Fi", 2010, 8.8));
        repository.createMovie(new Movie("Titanic", "Romance", 1997, 7.9));
        repository.createMovie(new Movie("The Dark Knight", "Action", 2008, 9.0));

        System.out.println("\n=== READ ALL ===");

        repository.readAllMovies();

        System.out.println("\n=== READ BY ID ===");

        Movie foundMovie = repository.findById(1);

        if (foundMovie != null) {
            System.out.println("Found movie: " + foundMovie);
        } else {
            System.out.println("Movie not found.");
        }

        System.out.println("\n=== UPDATE ===");

        repository.updateMovie(
                2,
                new Movie("Titanic Updated", "Drama/Romance", 1997, 8.0)
        );

        repository.readAllMovies();

        System.out.println("\n=== DELETE ===");

        repository.deleteMovie(3);

        repository.readAllMovies();
    }
}
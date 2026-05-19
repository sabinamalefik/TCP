package org.example;

import java.sql.*;

public class MovieRepository {

    private static final String URL = "jdbc:h2:mem:moviesdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS movies (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    genre VARCHAR(50) NOT NULL,
                    release_year INT NOT NULL,
                    rating DOUBLE NOT NULL
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
            System.out.println("Table movies created successfully.");

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void createMovie(Movie movie) {
        String sql = """
                INSERT INTO movies (title, genre, release_year, rating)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3, movie.getReleaseYear());
            statement.setDouble(4, movie.getRating());

            statement.executeUpdate();

            System.out.println("Movie added successfully: " + movie.getTitle());

        } catch (SQLException e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    public void readAllMovies() {
        String sql = "SELECT * FROM movies";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("\nMovies from database:");

            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("release_year"),
                        resultSet.getDouble("rating")
                );

                System.out.println(movie);
            }

        } catch (SQLException e) {
            System.out.println("Error reading movies: " + e.getMessage());
        }
    }

    public Movie findById(int id) {
        String sql = "SELECT * FROM movies WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("release_year"),
                        resultSet.getDouble("rating")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding movie: " + e.getMessage());
        }

        return null;
    }

    public void updateMovie(int id, Movie movie) {
        String sql = """
                UPDATE movies
                SET title = ?, genre = ?, release_year = ?, rating = ?
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3, movie.getReleaseYear());
            statement.setDouble(4, movie.getRating());
            statement.setInt(5, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Movie updated successfully.");
            } else {
                System.out.println("Movie with id " + id + " was not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating movie: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Movie deleted successfully.");
            } else {
                System.out.println("Movie with id " + id + " was not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting movie: " + e.getMessage());
        }
    }
}
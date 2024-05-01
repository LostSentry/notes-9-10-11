import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databasePath;

    Database(String databasePath) {
        // create table

        this.databasePath = databasePath;

      try ( Connection connection = DriverManager.getConnection(databasePath);
          Statement statement = connection.createStatement()) {

          statement.execute("CREATE TABLE IF NOT EXISTS" +
                  "movies ( id INTEGER PRIMARY KEY, " +
                  "name text, " +
                  "stars INTEGER," +
                  " watched BOOLEAN)");

      } catch (SQLException e) {
          System.out.println("Error creating movie DB table beacuse " + e);
      }
    }

    public void addNewMovie(Movie movie) {

        String insertSQL = "INSERT INTO movies (name, stars, watched) VALUES (?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(databasePath);
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {


            // insert into movies values
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getStars());
            preparedStatement.setBoolean(3, movie.isWatched());

            preparedStatement.execute();

        }catch (SQLException e) {
            System.out.println("error adding movie " + movie + " because " + e);
        }
    }

    public List<Movie> getAllMovies() {

        try (Connection connection = DriverManager.getConnection(databasePath);
        Statement statement = connection.createStatement()
        ) {

            // get all movies
            ResultSet movieResults = statement.executeQuery("SELECT * FROM movies");

            List<Movie> movies = new ArrayList<>();

            while (movieResults.next()) {
                int id = movieResults.getInt();
                String name = movieResults.getString("name");
                int stars = movieResults.getInt("stars");
                boolean watched = movieResults.getBoolean("watched");

                Movie movie = new Movie(name,stars,watched);
                movies.add(movie);
            }

            return movies;

        } catch (SQLException e) {
            System.out.println("error because " + e);
            return null;
        }

    }

    public List<Movie> getAllMoviesByWatched(boolean watchedStatus) {

        try(Connection connection = DriverManager.getConnection(databasePath);
          PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies WHERE watched = ?")) {

            preparedStatement.setBoolean(1, watchedStatus);
            ResultSet movieResults = preparedStatement.executeQuery();

            List<Movie> movies = new ArrayList<>();

            while (movieResults.next()) {
                int id = movieResults.getInt("id");
                String name = movieResults.getString("name");
                int stars = movieResults.getInt("stars");
                boolean watched = movieResults.getBoolean("watched");
                Movie movie = new Movie(id,name,stars,watched);
                movies.add(movie);
            }

            return movies;

        } catch (SQLException e) {
            System.out.println("Error getting movies by watched because " + e);
            return null;
        }
    }

    public void updateMovie(Movie movie) {

        String sql = "UPDATE movies SET stars = ?, watched = ? WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, movie.getStars());
            preparedStatement.setBoolean(2, movie.isWatched());
            preparedStatement.setString(3, movie.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("error updating because " + e);
        }


    }

    public void deleteMovie(Movie movie) {

        try(Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movies WHERE id = ?")) {

        preparedStatement.setInt(1, movie.getId());
        preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(" error deleting because " + e);
        }

    }

    public List<Movie> search(String searchTerm) {

        String sql = "SELECT * FROM movies WHERE UPPER(name) LIKE UPPER(?)";

        try(Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + searchTerm + "%");

            ResultSet movieResults = preparedStatement.executeQuery();

            List<Movie> movies = new ArrayList<>();

            while (movieResults.next()) {
                int id = movieResults.getId("id");
                String name = movieResults.getString("name");
                int stars = movieResults.getInt("stars");
                boolean watched = movieResults.getBoolean("watched");

                Movie movie = new Movie(id,name, stars, watched);
                movies.add(movie);
            }

            return movies;

        } catch (SQLException e) {
            System.out.println("Error searching because " + e);
            return null;
        }

    }

}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private String databasePath;

    Database(String databasePath) {
        // create table

        this.databasePath = databasePath;

      try ( Connection connection = DriverManager.getConnection(databasePath);
          Statement statement = connection.createStatement()) {

          statement.execute("CREATE TABLE IF NOT EXISTS" +
                  "movies (name text, stars INTEGER, watched BOOLEAN)");

      } catch (SQLException e) {
          System.out.println("Error creating movie DB table beacuse " + e);
      }
    }

    public void addNewMovie(Movie movie) {

        try(Connection connection = DriverManager.getConnection(databasePath);
        Statement statement = connection.createStatement()) {

            //todo

        }catch (SQLException e) {
            System.out.println("error adding movie " + movie + " because " + e);
        }
    }
}
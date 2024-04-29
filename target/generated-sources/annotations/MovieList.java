public class MovieList {

   private static final String DB_PATH = "jdbc:sqlite:movie_watchlist.sqlite";
   private static Database database;
    public static void main(String[] args) {
        database = new Database(DB_PATH);
    }
}
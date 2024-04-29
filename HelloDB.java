mport java.sql.*;

public class HelloDB {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:sqlite:hello.sqlite";

        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String createTableSql = "CREATE TABLE cats (name TEXT, age INTEGER)";
        statement.execute(createTableSql);

        String insertDataSql = "INSERT INTO cats VALUES ('Maru', 10)";
        statement.execute(insertDataSql);

        insertDataSql = "INSERT INTO cats VALUES ('Hello Kitty', 40)";
        statement.execute(insertDataSql);

        String getAllDataSql = "SELECT * FROM cats";
        ResultSet allCats = statement.executeQuery(getAllDataSql);

        while (allCats.next()) {
            String name = allCats.getString("name");
            int age = allCats.getInt("age");
            System.out.println(name + " is " + age + " years old.");
        }

        connection.close();
    }
}


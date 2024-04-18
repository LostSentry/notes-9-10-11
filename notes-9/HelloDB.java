public class HelloDB {
    public static void main(String[] args) throws SQLException {
        String url = "jbc:sqlite:hello.sqlite";

        Connection connection = DriverManager.getConnection(url)
        Statement statement = connection.createStatement();

        String createTableSQL = "CREATE TABLE IF NOT EXISTS cats (name TEXT, age, INTEGER)";
        statement.execute(createTableSQL);

        String insertDataSQL = "INSERT INTO cats 'values ('Maru, 10')";
        statement.execute(insertDataSQL);

        insertDataSQL = "INSERT INTO cats VALUES ('Hello Kitty, 45')";
        statement.execute(insertDataSQL);

        insertDataSQL = "INSERT INTO cats VALUES ('Garfield, 41')";
        statement.execute(insertDataSQL);

        insertDataSQL = "INSERT INTO cats VALUES ('Snowball, 12')";
        statement.execute(insertDataSQL);

        String getAllDataSQL ="SELECT * FROM cats";
        ResultSet allCats = statement.executeQuery(getAllDataSQL);

        while (allCats.next()) {
            String name = allCats.getString("name");
            int age = allCats.getInt("age");
            System.out.println(name + " is " + age + " years old.");
        }
        allCats.close();  // close results set

        String dropTableSql = "DROP TABLE cats";  //Delete - drop - cats table
        // statement.execute(dropTableSql);  // execute drop table

        statement.close();  // close statement
        connection.close(); // close connection
    }
}
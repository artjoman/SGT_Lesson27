import java.sql.*;

public class Lesson27 {

    public static void main(String[] args) {
        try {
            String dbUrl = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(dbUrl);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();

                System.out.println("Connected to: " + dm.getDatabaseProductName() + " " + dm.getDatabaseProductVersion());

                // Container where we will put our SQL query
                Statement stmt = conn.createStatement();

                // SQL query itself
                // First we create the table(s)
                String query = "CREATE TABLE students" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "grade INT )"
                        ;


                conn.close();
            }

        } catch (SQLException ex) {
            System.out.println("There was an SQL exception");
        }
    }
}

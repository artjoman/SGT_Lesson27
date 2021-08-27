import java.sql.*;
import java.util.Scanner;

public class Lesson27_2 {

    public static void main(String[] args) {

        try {

            String dbUrl = "jdbc:sqlite:database_27.db";
            Connection conn = DriverManager.getConnection(dbUrl);

            if( conn != null ) {
                DatabaseMetaData databaseMetadata = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Connected to " + databaseMetadata.getDatabaseProductName() + " " + databaseMetadata.getDatabaseProductVersion());

                // CREATING A TABLE
                Statement statement = conn.createStatement();
                String sqlStatement =
                        "CREATE TABLE IF NOT EXISTS groceries"+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT NOT NULL," +
                                "expiry_date TEXT NOT NULL," +
                                "shop_id INTEGER," +
                                "FOREIGN KEY(shop_id) REFERENCES shops(id) )";
                statement.execute(sqlStatement);

                String somethingRetrievedFromScanner = "NOT A JUICE";
                // INSERT FIRST ROW
                sqlStatement = "INSERT INTO groceries (name, expiry_date)" +
                        "values ( '" +  somethingRetrievedFromScanner + "', '2021-09-09' )";
                statement.execute(sqlStatement);

                sqlStatement = "SELECT * FROM groceries";
                ResultSet resultSet = statement.executeQuery(sqlStatement);

                while ( resultSet.next() ) {
                    String productName = resultSet.getString("name");
                    String expiryDate = resultSet.getString("expiry_date");
                    int id = resultSet.getInt("id");

                    System.out.println("ID:" + id + " name: " + productName + " ExpiryDate: " + expiryDate);
                }

                sqlStatement = "DELETE FROM groceries" +
                        " WHERE id = 2";
                statement.execute(sqlStatement);


                sqlStatement =
                        "CREATE TABLE IF NOT EXISTS shops"+
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT NOT NULL," +
                                "address TEXT NOT NULL)";
                statement.execute(sqlStatement);


                sqlStatement = "SELECT shops.name as sname, groceries.name as gname, groceries.expiry_date " +
                        "FROM shops LEFT JOIN groceries " +
                        "ON groceries.shop_id = shops.id";

                resultSet = statement.executeQuery(sqlStatement);

                while ( resultSet.next() ) {
                    String shopName = resultSet.getString("sname");
                    String productName = resultSet.getString("gname");
                    String expiryDate = resultSet.getString("expiry_date");

                    System.out.println("SHOP:" + shopName + " name: " + productName + " ExpiryDate: " + expiryDate);
                }

            }



        } catch( SQLException exception ) {
            System.out.println("An error has occured" + exception.toString());
        }


    }

}

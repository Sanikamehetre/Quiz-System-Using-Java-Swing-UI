import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


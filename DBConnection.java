import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    // Static data members — shared across all uses (no object needed)
    private static final String URL      = "jdbc:mysql://localhost:3306/payroll_db";
    private static final String USER     = "root";
    private static final String PASSWORD = "your_password_here"; 

    private static Connection conn = null;

    public static Connection getConnection() throws DatabaseException {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to database successfully.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Could not connect to database. " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

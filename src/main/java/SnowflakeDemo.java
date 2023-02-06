import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SnowflakeDemo {

    private static final String JDBC_STRING_TEMPLATE = "jdbc:snowflake://%s.snowflakecomputing.com/?";

    private static final ResourceBundle rb = ResourceBundle.getBundle("db");

    static {
        try {
            Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getException());
        }
    }

    public static void main(String[] args) {
        final String user = rb.getString("db.user");
        final String pwd = rb.getString("db.password");

        try (Connection connection = DriverManager.getConnection(composeJdbcString(), user, pwd)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed! " + e.getMessage());
        }
    }

    public static String composeJdbcString() {
        return String.format(JDBC_STRING_TEMPLATE,
                rb.getString("db.account"));

//        return String.format(JDBC_STRING_TEMPLATE,
//                rb.getString("db.account"),
//                rb.getString("db.warehouse"),
//                rb.getString("db.schema"),
//                rb.getString("db.database"));
    }
}

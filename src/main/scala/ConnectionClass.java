import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private static final String DRIVER_PATH="com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL="jdbc:mysql://localhost:3306/java_class_db";
    private static final String USERNAME="root";
    private static final String PASSWORD="admin";

    public ConnectionClass(){    //CONSTRUCTOR
        try {
            Class.forName(DRIVER_PATH);
        }catch (Exception e){
            throw new RuntimeException("SOMETHING WENT WRONG" + e);
        }
    }

    public Connection getConnection() throws SQLException {   //METHOD
        return DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);

    }
}

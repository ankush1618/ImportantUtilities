package db.jdbc.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcConnection {

    public static void main(String[] args) throws SQLException {
        Properties prop=Utility.readPropertyFile("config.properties");
        String user=prop.getProperty("user");
        String pass=prop.getProperty("password");
        Connection conn=Utility.connect(Constants.getConnectionString(),user,pass);
        Statement statement=conn.createStatement();
        ResultSet res=statement.executeQuery("Select * from employees");
        while(res.next()){
            System.out.println(res);
            //System.out.println(res.getString(2));
        }
        try {
            // Close the connection when done
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
}

package db.jdbc.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Utility {


    public static Properties readPropertyFile(String filepath){
          Properties prop=new Properties();
          try(InputStream input=Utility.class.getClassLoader().getResourceAsStream(filepath)){
              prop.load(input);
          }
          catch(IOException e){
              e.printStackTrace();
          }
          return prop;
    }

    //Methods for DB Connection
    public static Connection connect(String url,String user,String pass) throws SQLException {
        Connection con= DriverManager.getConnection(url,user,pass);
        return con;
    }

    public static Statement statement(Connection conn) throws SQLException {
        Statement state=conn.createStatement();
        return state;
    }

    public static ResultSet executeQueries(Statement statement, String query) throws SQLException {
        ResultSet res=statement.executeQuery(query);
        return res;
    }

    public static void main(String[] args) {

        Properties prop=readPropertyFile("config.properties");
        System.out.println(prop.getProperty("user"));
        System.out.println(prop.getProperty("password"));
    }
}

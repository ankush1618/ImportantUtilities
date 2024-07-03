package db.jdbc.connection;

public class Constants {

    private static String CONNECTIONSTRING="jdbc:mysql://localhost:3306/demo";

    public static String getConnectionString(){
        return CONNECTIONSTRING;
    }
}

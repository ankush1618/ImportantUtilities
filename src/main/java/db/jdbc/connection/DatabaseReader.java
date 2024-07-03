package db.jdbc.connection;

import java.sql.*;
import java.util.*;

public class DatabaseReader {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/demo";
        String username = "komal";
        String password = "Football@10";

        // SQL query to retrieve data
        String query = "SELECT * FROM employees";

        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Creating a statement
            Statement statement = connection.createStatement();

            // Executing the query
            ResultSet resultSet = statement.executeQuery(query);

            // Getting metadata to retrieve column names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Map to store column data
            Map<String, String[]> dataMap = new HashMap<>();

            // Iterating over the result set
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    // Getting column name
                    String columnName = metaData.getColumnName(i);

                    // Getting column data
                    String columnData = resultSet.getString(i);

                    // Storing column data in the map
                    if (!dataMap.containsKey(columnName)) {
                        dataMap.put(columnName, new String[]{columnData});
                    } else {
                        String[] existingData = dataMap.get(columnName);
                        String[] updatedData = Arrays.copyOf(existingData, existingData.length + 1);
                        updatedData[existingData.length] = columnData;
                        dataMap.put(columnName, updatedData);
                    }
                }
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();

            // Printing the map
            for (Map.Entry<String, String[]> entry : dataMap.entrySet()) {
                System.out.println("Column Name: " + entry.getKey());
                System.out.println("Column Data: " + Arrays.toString(entry.getValue()));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

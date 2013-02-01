package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by: Pavel Romanenko
 * mail: promanenko@griddynamics.com
 * Date: 01.02.13
 * Time: 18:17
 */

public class ConnectionFactory {

    private Connection connection;

    public Connection getConnection() {
        if (connection == null){
            initializeConnection();
        }
        return connection;
    }

    public void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();  //TODO exception handling
            }
        }
    }

    private void initializeConnection(){
        try {
            //TODO move properties to separate file
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/Library?"
                    + "user=root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //TODO exception handling
        } catch (SQLException e) {
            e.printStackTrace(); //TODO exception handling
        }
    }
}

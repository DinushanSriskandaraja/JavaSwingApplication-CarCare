package DatabaseLayer;

import java.sql.*;

public class DatabaseConnection {
    private final String URL="jdbc:mysql://localhost:3306/carcare_db";
    private final String UName="root";
    private final String Password="";
    private static DatabaseConnection instance;
    private Connection con;

    public DatabaseConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(URL,UName,Password);
            System.out.println("Database Connection Sucess");
        }catch (ClassNotFoundException ex)
        {
            System.out.println("Driver Class Error "+ex.getMessage());
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

}


package Models;
import Controlers.inventory;
import Views.Inventory;

import java.sql.*;

public class InventoryDbConnector {
    private static Connection connection;


    public InventoryDbConnector()
    {
        String url="jdbc::mysql://le7jwyex56dcv1vs";
        String user="w0i7yta26bznpg95";
        String password="pwo8964ak69169uc";

        try{
            connection = DriverManager.getConnection(url,user,password);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }
    public static void addInventory(){

        String sql="INSERT INTO inventory(name,description,quantity,price,supplier) VALUES(?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, inventory.getName());
            preparedStatement.setString(2, inventory.getDescription());
            preparedStatement.setInt(3, inventory.getQuantity());
            preparedStatement.setFloat(4, inventory.getPrice());
            preparedStatement.setString(5, inventory.getSupplier());

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insert was successful
            if (rowsAffected > 0) {
                // Data added successfully
                System.out.println("Order details added to the database.");

                //                JOptionPane.showMessageDialog(Component, "Order details added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Inventory.showMessage("Produvt Added Successfully","Success");
            } else {
                // Failed to add data
                Inventory.showMessage("Produvt Added Successfully","Error");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}

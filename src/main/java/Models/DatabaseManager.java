package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/suppliers?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "";


            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // add supplier need pass the supplier
    public void addSupplier(Supllier supllier)
    {
        String query="INSERT INTO suppliers(supplierName,address,email) values(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            // return the values
            preparedStatement.setString(1, supllier.getSupplier_name());
            preparedStatement.setString(2, supllier.getSupplier_address());
            preparedStatement.setString(3, supllier.getSupplier_email());

            // if the affected rows == 0 it will print a error
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0)
            {
                throw new SQLException("Adding the supplier is failed, Please Check And Try Again");
            }

            // to get suppIDs
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                // checking for rows in generated keys
                if(generatedKeys.next())
                {
                    supllier.setSupplier_id(generatedKeys.getInt(1));
                }
                else
                {
                    throw new SQLException("Adding Supplier Is Failed, ID is not generated");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public List<Supllier> getAllSuppliers()
    {
        List<Supllier> suppliers=new ArrayList<>();

        String query="Select * from suppliers";

        try(Statement statement = connection.createStatement();

            ResultSet resultSet= statement.executeQuery(query))
        {
            while (resultSet.next())
            {
                Supllier supllier=new Supllier();
                supllier.setSupplier_id(resultSet.getInt("supplierID"));
                supllier.setSupplier_name(resultSet.getString("supplierName"));
                supllier.setSupplier_address(resultSet.getString("address"));
                supllier.setSupplier_email(resultSet.getString("email"));
                suppliers.add(supllier);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        return suppliers;


    }


    public void updateSupplier(Supllier supllier)
    {
        String query="UPDATE suppliers SET supplierName= ?, address = ?, email= ? WHERE supplierID=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, supllier.getSupplier_name());
            preparedStatement.setString(2, supllier.getSupplier_address());
            preparedStatement.setString(3, supllier.getSupplier_email());
            preparedStatement.setInt(4, supllier.getSupplier_id());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void removeSupplier(int supplierId)
    {
        String query="DELETE FROM suppliers WHERE supplierID= ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, supplierId);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }



    }





}
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager()
    {
        String url="jdbc::mysql://localhost:3306/supplier?useUnicode=true&charcterEncoding=UTF-8";
        String user="root";
        String password="";

        try{
            connection = DriverManager.getConnection(url,user,password);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void addSupplier(Supllier supllier)
    {
        String query="INSERT INTO supplier(name, Address, Email) values(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, supllier.getSupplier_name());
            preparedStatement.setString(2, supllier.getSupplier_address());
            preparedStatement.setString(3, supllier.getSupplier_email());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0)
            {
                throw new SQLException("Adding the supplier is failed, Please Check And Try Again");
            }

            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
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

        String query="Select * from supllier";

        try(Statement statement = connection.createStatement();

            ResultSet resultSet= statement.executeQuery(query))
        {
            while (resultSet.next())
            {
                Supllier supllier=new Supllier();
                supllier.setSupplier_id(resultSet.getInt("id"));
                supllier.setSupplier_name(resultSet.getString("name"));
                supllier.setSupplier_address(resultSet.getString("Address"));
                supllier.setSupplier_email(resultSet.getString("Email"));
                suppliers.add(supllier);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        return suppliers;


    }





}
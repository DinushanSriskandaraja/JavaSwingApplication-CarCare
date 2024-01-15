package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.CustomerMailing;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerService {

    private DatabaseConnection singleConn;
    public CustomerService()
    {
        singleConn=new DatabaseConnection();
    }
    public ArrayList getCustomerDetails(CustomerMailing customer)
    {
        ArrayList <String> data = new ArrayList<>();
        System.out.println("Done this part ArrayList");

        try
        {
            String query="select Customer_Email,Customer_Name from customer_order Where Order_ID='"+customer.Customer_ID+"'";
            Statement statement = singleConn.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            System.out.println("Done this part query getting");

            if (result.next())
            {
                String email = result.getString("Customer_Email");
                String name = result.getString("Customer_Name");
                data.add(email);
                data.add(name);
                System.out.println("Email: " + email + ", Name: " + name);
                System.out.println("Done this part retrieve data from DB");
            } else {
                System.out.println("No records found.");
            }
        }catch (Exception ex)
        {
            System.out.println("Cannot get the Customer Details\n"+ex.getMessage());

        }
        return data;
    }
    public boolean checkMail(CustomerMailing customer){
        ArrayList<String> check = getCustomerDetails(customer);
        if(check==null){
            return false;
        } else if (check.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}

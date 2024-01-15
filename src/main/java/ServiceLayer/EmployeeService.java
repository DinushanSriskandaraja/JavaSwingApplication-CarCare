package ServiceLayer;

import DatabaseLayer.DatabaseConnection;
import Models.EmployeeMailing;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class EmployeeService {

    private DatabaseConnection singleConn;
    public EmployeeService()
    {
        singleConn=new DatabaseConnection();
    }
    public ArrayList getEmployeeDetails(EmployeeMailing employee)
    {
        ArrayList <String> data = new ArrayList<>();

        try
        {
            String query1="select Emp_Email,Emp_Name from employee Where Emp_ID='"+employee.employee_ID+"'";
            String query2="update employee set Role='"+employee.employee_Role+"' where Emp_ID='"+employee.employee_ID+"'";
            // update role db
            Statement selectStatement = singleConn.getConnection().createStatement();
            Statement updateStatement = singleConn.getConnection().createStatement();
            ResultSet result = selectStatement.executeQuery(query1);
            updateStatement.executeUpdate(query2);
            System.out.println("Done this part query getting");

            if (result.next())
            {
                String email = result.getString("Emp_Email");
                String name = result.getString("Emp_Name");
                data.add(email);
                data.add(name);
                System.out.println("Email: " + email + ", Name: " + name);
            } else {
                System.out.println("No records found.");
            }
        }catch (Exception ex)
        {
            System.out.println("Cannot get the Customer Details\n"+ex.getMessage());

        }
        return data;
    }
    public boolean checkMail(EmployeeMailing employee){
        ArrayList<String> check = getEmployeeDetails(employee);
        if(check==null){
            return false;
        } else if (check.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}

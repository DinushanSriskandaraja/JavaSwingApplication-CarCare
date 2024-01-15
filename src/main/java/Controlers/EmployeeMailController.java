package Controllers;


import Models.EmployeeMailing;
import ServiceLayer.EmployeeService;

import java.util.*;

public class EmployeeMailController {
    EmployeeMailing empObj;
    EmployeeService service;
    public EmployeeMailController() {service=new EmployeeService();
    }
    public EmployeeMailing setID(String empID,String empRole) {
        empObj = new EmployeeMailing(empID,empRole);
        return empObj;
    }
    public ArrayList<String> getDetailsFromDB()
    {
        System.out.println("Done this part controller get details and cus"+empObj);
        return service.getEmployeeDetails(empObj);
    }

    public boolean validation(){
        return service.checkMail(empObj);
    }

}








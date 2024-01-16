package ModelsUnitTests;


import Controllers.EmployeeMailController;
import Models.EmployeeMailing;

import ServiceLayer.EmployeeService;
import org.junit.jupiter.api.*;
import java.util.*;

public class EmployeeMailTest {
    static EmployeeMailing modelObj;
    static EmployeeMailController controllerObj;
    static EmployeeService serviceObj;
    String Mail;
    String Name ;
    boolean status;

    @BeforeAll
    public static void InitTest()
    {
        controllerObj = new EmployeeMailController();
        serviceObj = new EmployeeService();
    }

    @BeforeEach
    public void setValues()
    {
        EmployeeMailing modelTest = controllerObj.setID("123","Mechanic");
        ArrayList<String> Details = controllerObj.getDetailsFromDB();
        Mail = Details.get(0);
        Name = Details.get(1);
        status = controllerObj.validation();
    }


    @Test
    @Disabled("Because It will be validated Default when the Program Runs")
        public void emailTesting() {
        Assertions.assertTrue(status);
    }

    @Test
    public void testDetailsFromDB() {
        Assertions.assertEquals("carcaremailer@gmail.com", Mail);
        Assertions.assertEquals("Test", Name);
    }

    @AfterEach
    public void deleteValues()
    {
        modelObj = null;
        ArrayList<String> Details = null;
        Mail = "";
        Name = "";
        status = false;
    }

    @AfterAll
    public static void clearAll()
    {
        controllerObj = null;
        serviceObj = null;
    }
}

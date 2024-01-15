package ModelsUnitTests;

import Controllers.CustomerMailController;
import Models.CustomerMailing;
import ServiceLayer.CustomerService;

import org.junit.jupiter.api.*;
import java.util.*;

public class CustomerMailUnitTest{

    static CustomerMailing modelTest;
    static CustomerMailController controllerTest;
    static CustomerService serviceTest;
    String T_mail;
    String T_Name ;
    boolean check;

    @BeforeAll
    public static void InitTest()
    {
        controllerTest = new CustomerMailController();
        serviceTest = new CustomerService();
    }

    @BeforeEach
    public void setValues()
    {
        CustomerMailing modelTest = controllerTest.setID("123");
        ArrayList<String> testDetails = controllerTest.getDetailsFromDB();
        T_mail = testDetails.get(0);
        T_Name = testDetails.get(1);
        check = controllerTest.validation();
    }

    @Test
    public void checkEmailSent() {
        Assertions.assertTrue(check);
    }

    @Test
    public void testDetailsFromDB() {
          Assertions.assertEquals("carcaremailer@gmail.com", T_mail);
        Assertions.assertEquals("Test", T_Name);
    }

    @AfterEach
    public void clearValues()
    {
        modelTest = null;
        ArrayList<String> testDetails = null;
        T_mail = null;
        T_Name = null;
        check = false;
    }

    @AfterAll
    public static void clearAll()
    {
        controllerTest = null;
        serviceTest = null;
    }
}
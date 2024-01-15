package Views;

import Controllers.EmployeeMailController;
import Models.EmployeeMailing;
import Utilities.MailUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeMailUI extends JFrame{
    private JButton btnAllocate;
    private JComboBox dropboxRole;
    private JTextField txtEmpID;
    public JPanel backPanel;

    EmployeeMailController MailC;

    public EmployeeMailUI() {
        MailC = new EmployeeMailController();
        MailUtil MailUtility ;

        btnAllocate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //button click event handling
                String CustID = txtEmpID.getText().toString();
                String Role = dropboxRole.getSelectedItem().toString();
                    //collaborates with EmployeeMailController(MailC)
                    EmployeeMailing CustomerOB = MailC.setID(CustID,Role);
                    boolean validate = MailC.validation();
                    if(validate){
                        ArrayList<String> data = MailC.getDetailsFromDB();
                        String Email=data.get(0);
                        String Empname=data.get(1);
                        MailUtil.sendMailEmployee(Email,Empname,Role);
                        JOptionPane.showMessageDialog(backPanel, "Email Sent Successful !", "Success", 1);
                        JOptionPane.showMessageDialog(backPanel, "Role Updated !", "Success", 1);
                    }
                    else{
                        JOptionPane.showMessageDialog(backPanel, " Email Not Sent", "Error", 0);
                    }
            }
        });
    }

    public static void main(String[] args) {
        EmployeeMailUI ui=new EmployeeMailUI();
        ui.setContentPane(ui.backPanel);
        ui.setTitle("EmployeeMail 1.0");
        ui.setSize(600,600);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }
}
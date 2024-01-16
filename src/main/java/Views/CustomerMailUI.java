package Views;

import Controllers.CustomerMailController;
import Models.CustomerMailing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Utilities.MailUtil.sendMailCustomer;

public class CustomerMailUI extends JFrame{
    private JButton btnUpdate;
    private JComboBox dropboxStatus;
    private JTextField txtCustID;
    private JPanel backPanel;

    CustomerMailController MailC;

    public CustomerMailUI() {
        MailC = new CustomerMailController();

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CustID = txtCustID.getText().toString();
                String Status = dropboxStatus.getSelectedItem().toString();
                if(Status.equalsIgnoreCase("Ready")){
                    CustomerMailing CustomerOB = MailC.setID(CustID);
                    boolean validate = MailC.validation();
                    if(validate){
                        ArrayList<String> Details = MailC.getDetailsFromDB();
                        String mail=Details.get(0);
                        String Name=Details.get(1);
                        sendMailCustomer(mail,Name);
                        JOptionPane.showMessageDialog(backPanel, "Email Sent Successful !", "Success", 1);
                    }
                    else{
                        JOptionPane.showMessageDialog(backPanel, " Email Not Sent", "Error", 0);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(backPanel, "Vehicle is not Ready !", "Error", 0);
                    System.out.println("Vehicle is not Ready");
                }
            }
        });
    }

    public static void main(String[] args) {
        CustomerMailUI ui=new CustomerMailUI();
        ui.setContentPane(ui.backPanel);
        ui.setTitle("CustomerMail 1.0");
        ui.setSize(600,600);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }
}
package Views;

import Controlers.SupplierController;
import Models.DatabaseManager;
import Models.Supllier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierManagementUI {

    // made the objects of componenets
    private JFrame frame;
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;



    private SupplierController controller;


    public SupplierManagementUI()
    {
        //this.controller=sc;
        frame=new JFrame("Supplier Management");
        tableModel = new DefaultTableModel();
        supplierTable =new JTable(tableModel);
        nameField=new JTextField();
        addressField =new JTextField();
        emailField =new JTextField();
        addButton=new JButton("Add");
        updateButton=new JButton("Update");
        deleteButton=new JButton("Delete");
        initializeUI();




        // if we press the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // validation
                if(!nameField.getText().isEmpty() && !emailField.getText().isEmpty() && !addressField.getText().isEmpty())
                    controller.addSupplier(nameField.getText(), emailField.getText(), addressField.getText());
                else
                {
                    JOptionPane.showMessageDialog(frame,"Please Fill In The All Fields And Try Again!!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nameField.getText().isEmpty() && !emailField.getText().isEmpty() && !addressField.getText().isEmpty()) {
                    int selectedRow = supplierTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int supllierId = (int) supplierTable.getValueAt(selectedRow, 0);
                        controller.updateSupplier(supllierId, nameField.getText(), emailField.getText(), addressField.getText());

                    } else {
                        JOptionPane.showMessageDialog(frame, "Please Fill In The All Fields And Try Again!!!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Please Fill In The All Fields And Try Again!!!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow=supplierTable.getSelectedRow();
                if (selectedRow != -1)
                {
                    int supllierId=(int)supplierTable.getValueAt(selectedRow,0);
                    controller.removeSupplier(supllierId);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Please Select A Row To Update!!!");
                }

            }
        });

        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void setController(SupplierController controller)
    {
        this.controller=controller;
    }

    // initailizing the coulmns
    private void initializeUI()
    {
        frame.setLayout(new BorderLayout());
        String[] columns={"Supplier Id", "Supplier Name", "Supplier Address", "Supplier Email"};
        tableModel.setColumnIdentifiers(columns);

        // can scroll the table
        JScrollPane scrollPane= new JScrollPane(supplierTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // took a new panel to make the rows and columns
        JPanel inputPanel = new JPanel(new GridLayout(6,2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

        frame.add(inputPanel,BorderLayout.SOUTH);

    }


    public void loadSupplier(List<Supllier> suplliers)
    {
        tableModel.setRowCount(0);
        for (Supllier supllier: suplliers)
        {
            Object[] rowData={supllier.getSupplier_id(),supllier.getSupplier_name(),supllier.getSupplier_address(),supllier.getSupplier_email()};
            tableModel.addRow(rowData);
        }
    }




    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->{
            DatabaseManager databaseManager1=new DatabaseManager();
            SupplierManagementUI view=new SupplierManagementUI();
            SupplierController controller1= new SupplierController(databaseManager1,view);
        });
    }



}
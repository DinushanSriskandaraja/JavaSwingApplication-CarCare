package car_app;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Customer extends JFrame {
    private JPanel Main;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtAmount;
    private JTextField txtEmail;
    private JTextField txtDate;
    private JTable table1;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JScrollPane table_1;
    private JButton BACKTOMENUButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Customer");
        frame.setContentPane(new Customer().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    Connection con;
    PreparedStatement insert;

    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerorder","root","");
            System.out.println("Successs");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    void table_load()
    {

        try
        {
            insert = con.prepareStatement("SELECT * FROM Customer");
            ResultSet rs = insert.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Customer() {
        connect();
        table_load();
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String orderid,cusname,amount,cusmail,date;
                orderid=txtID.getText();
                cusname = txtName.getText();
                amount = txtAmount.getText();
                cusmail = txtEmail.getText();
                date=txtDate.getText();

                try {
                    insert = con.prepareStatement("insert into Customer(OrderID,Cus_Name,Amount,CusMail,Date)values(?,?,?,?,?)");
                    insert.setString(1, orderid);
                    insert.setString(2, cusname);
                    insert.setString(3, amount);
                    insert.setString(4, cusmail);
                    insert.setString(5, date);
                    insert.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                    table_load();
                    txtID.setText("");
                    txtName.setText("");
                    txtAmount.setText("");
                    txtEmail.setText("");
                    txtDate.setText("");
                    txtID.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }


        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                int selectedIndex = table1.getSelectedRow();

                int CusID = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                String orderid,cusname,amount,cusmail,date;
                orderid=txtID.getText();
                cusname = txtName.getText();
                amount = txtAmount.getText();
                cusmail = txtEmail.getText();
                date=txtDate.getText();


                try {

                    insert = con.prepareStatement("update Customer set OrderID = ?,Cus_Name = ?,Amount = ?,CusMail=?,Date=? where CusID = ?");
                    insert.setString(1,orderid);
                    insert.setString(2,cusname);
                    insert.setString(3,amount);
                    insert.setString(4,cusmail);
                    insert.setString(5,date);
                    insert.setInt(6,CusID);

                    insert.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Updated Succesfully");

                    table_load();
                    txtID.setText("");
                    txtName.setText("");
                    txtAmount.setText("");
                    txtEmail.setText("");
                    txtDate.setText("");
                    txtID.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }




            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                int selectedIndex = table1.getSelectedRow();
                txtID.setText(model.getValueAt(selectedIndex, 1).toString());
                txtName.setText(model.getValueAt(selectedIndex, 2).toString());
                txtAmount.setText(model.getValueAt(selectedIndex, 3).toString());
                txtDate.setText(model.getValueAt(selectedIndex, 4).toString());
                txtEmail.setText(model.getValueAt(selectedIndex, 5).toString());
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                int selectedIndex = table1.getSelectedRow();
                try {

                    int CusID = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to Delete the record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){

                        insert = con.prepareStatement("delete from Customer where CusID = ?");

                        insert.setInt(1,CusID);
                        int i = insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Deleted");
                        txtID.setText("");
                        txtName.setText("");
                        txtAmount.setText("");
                        txtEmail.setText("");
                        txtDate.setText("");
                        txtID.requestFocus();
                        table_load();

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
            }
        });
    }

}

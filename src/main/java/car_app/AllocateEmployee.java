package car_app;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AllocateEmployee extends JFrame {
    private JTable table2;
    private JButton Allocatebutton1;
    private JButton button2;
    private JComboBox txtCid;
    private JComboBox txtEid;
    private JPanel Main;
    private JButton SELECTButton;
    private JButton SELECTButton1;
    private JButton BACKTOMENUButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AllocateEmployee");
        frame.setContentPane(new AllocateEmployee().Main);
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

            insert = con.prepareStatement("SELECT * FROM Allocate");
            ResultSet rs = insert.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public AllocateEmployee() {
        connect();
        table_load();
        Allocatebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Allocatebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                String OrID= txtCid.getSelectedItem().toString();
                String EmpID= txtEid.getSelectedItem().toString();




                try {

                    insert = con.prepareStatement("insert into Allocate (EmpID,OrderID)values(?,?)");
                    insert.setString(1,EmpID);
                    insert.setString(2,OrID);
                    insert.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Added Succesfully");

                    table_load();


                    txtCid.setSelectedItem("");
                    txtEid.setSelectedItem("");
                    txtCid.requestFocus();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Select ? ", "Warning", 2);

                }
            }
        });
        SELECTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    insert = con.prepareStatement("SELECT OrderID FROM Customer");
                    ResultSet Rs = insert.executeQuery();

                    while (Rs.next()) {
                        String id = Rs.getString("OrderID");
                        txtCid.addItem(id);

                    }


                }catch(SQLException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Select The Customer ID? ", "Alert", 1);
                }
            }
        });
        SELECTButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{


                    insert = con.prepareStatement("SELECT EmpID FROM Employee");
                    ResultSet Rs = insert.executeQuery();


                        while (Rs.next()) {
                            String id = Rs.getString("EmpID");
                            txtEid.addItem(id);

                        }


                }catch(SQLException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Select The Customer ID? ", "Alert", 1);
                }

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                int selectedIndex = table2.getSelectedRow();
                try {

                    int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to Delete the record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){

                        insert = con.prepareStatement("delete from Allocate where AllocateID = ?");

                        insert.setInt(1,id);
                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Deleted");
                        table_load();

                    }

                } catch ( SQLException ex) {
                    ex.printStackTrace();

                }
            }
        });
    }
}

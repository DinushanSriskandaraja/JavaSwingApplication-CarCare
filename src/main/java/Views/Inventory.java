package Views;

import Controlers.inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventory extends JFrame {

    private JTextField name;
    private JTextField description;
    private JTextField quantity;
    private JTextField unitprice;
    private JComboBox<String> supplier;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;

    public Inventory() {
        initComponents();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming you have an InventoryController class
                inventory.addInventory(
                        name.getText(),
                        description.getText(),
                        Integer.parseInt(quantity.getText()),
                        Float.parseFloat(unitprice.getText()),
                (String) supplier.getSelectedItem()
                );

                // Update the table if needed
                // tableModel.addRow(new Object[]{name.getText(), description.getText(), ...});
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your delete logic here
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your update logic here
            }
        });

        // Add the rest of your initialization code here
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void initComponents() {
        // Initialize components
        name = new JTextField();
        description = new JTextField();
        quantity = new JTextField();
        unitprice = new JTextField();
        supplier = new JComboBox<>();
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        inventoryTable = new JTable();
        tableModel = new DefaultTableModel();

        // Set layout and add components
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(name);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(description);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantity);
        inputPanel.add(new JLabel("Unit Price:"));
        inputPanel.add(unitprice);
        inputPanel.add(new JLabel("Supplier:"));
        inputPanel.add(supplier);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        // Use BorderLayout for better organization
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(inventoryTable), BorderLayout.SOUTH);

        // Set the table model for the JTable
        inventoryTable.setModel(tableModel);

        add(mainPanel);

        // Add the rest of your component initialization code here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inventory());
    }


}

package View;

import Controler.inventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventory extends javax.swing.JFrame {
    private static java.awt.Component Component;
    private JButton saveButton;
    private JTextField description;
    private JTextField quantity;
    private JTextField unitprice;
    private JTable table1;
    private JButton deleteButton;
    private JTextField name;
    private JComboBox supplier;
    private JScrollPane inventoryTable;

    public Inventory(JTextField description, JTextField quantity, JTextField unitprice, JTable table1, JTextField name, JComboBox supplier, JScrollPane inventoryTable){
        this.description = description;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.table1 = table1;
        this.name = name;
        this.supplier = supplier;
        this.inventoryTable = inventoryTable;

        saveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.setName(String.valueOf(name));
                inventory.setDescription(String.valueOf(description));
                inventory.setSupplier(String.valueOf(supplier));
                inventory.setQuantity(Integer.parseInt(String.valueOf(quantity)));
                inventory.setPrice(Float.parseFloat(String.valueOf(unitprice)));
                inventory.addInventory();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public static void showMessage(String message, String title){
        JOptionPane.showMessageDialog(Component, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}

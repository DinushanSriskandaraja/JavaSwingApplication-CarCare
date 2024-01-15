package Controlers;

import Models.InventoryDbConnector;

public class inventory {

    private static String name;
    private static String description;
    private static int quantity;
    private static float price;
    private static String supplier;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        inventory.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        inventory.description = description;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        inventory.quantity = quantity;
    }

    public static float getPrice() {
        return price;
    }

    public static void setPrice(float price) {
        inventory.price = price;
    }

    public static String getSupplier() {
        return supplier;
    }

    public static void setSupplier(String supplier) {
        inventory.supplier = supplier;
    }
    public static void addInventory(){
        InventoryDbConnector.addInventory();
//        System.out.println("Order details added to the database.");
//        JOptionPane.showMessageDialog(null, "Order details added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

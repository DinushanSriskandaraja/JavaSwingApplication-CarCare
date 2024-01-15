package Controlers;

import Models.InventoryDbConnector;


public class inventory  {
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        inventory.id = id;
    }

    private static int id;
    private static String name;
    private static String description;
    private static int quantity;
    private static float price;
    private static String supplier;

    public inventory(String id, String name, String description, int quantity, float price, String supplier) {
    }

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
    public static void addInventory(String id,String name, String description, int quantity, float price, String supplier) {
        // Create an Inventory object with the provided data
        inventory inventory = new inventory(id,name, description, quantity, price, supplier);

        // Call the method in InventoryDbConnector to add the inventory to the database
        InventoryDbConnector.addInventory(inventory);
    }
}

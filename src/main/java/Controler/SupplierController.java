package Controler;

import Model.DatabaseManager;
import Model.Supllier;
import View.SupplierManagementUI;

import java.util.List;

public class SupplierController {
    private DatabaseManager databaseManager;
    private SupplierManagementUI view;

    public SupplierController(DatabaseManager databaseManager, SupplierManagementUI view)
    {
        this.databaseManager=databaseManager;
        this.view=view;
        view.setController(this);
        updateView();

    }

    // in the UI it is passed the name,email, address
    public void addSupplier(String name,String email,String address)
    {

        Supllier supllier=new Supllier();
        supllier.setSupplier_name(name);
        supllier.setSupplier_address(address);
        supllier.setSupplier_email(email);

        databaseManager.addSupplier(supllier);
        updateView();


    }
    public void updateView()
    {
        List<Supllier> suplliers=databaseManager.getAllSuppliers();
        view.loadSupplier(suplliers);

    }


    public void updateSupplier(int supllierId,String name,String email,String address)
    {
        Supllier updateSupplier = new Supllier();
        updateSupplier.setSupplier_id(supllierId);
        updateSupplier.setSupplier_name(name);
        updateSupplier.setSupplier_address(address);
        updateSupplier.setSupplier_email(email);
        databaseManager.updateSupplier(updateSupplier);
        updateView();
    }


    public void removeSupplier(int id)
    {
        databaseManager.removeSupplier(id);
        updateView();
    }
}
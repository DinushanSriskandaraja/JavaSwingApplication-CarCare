package Model;

//njdnkj
class Supllier
{
    private int supplier_id;
    private String supplier_name;
    private String supplier_address;
    private String supplier_email;

    public Supllier()
    {

    }

    public Supllier(int supplier_id,String supllier_name,String supplier_address,String supplier_email )
    {
        this.supplier_id=supplier_id;
        this.supplier_name=supllier_name;
        this.supplier_address=supplier_address;
        this.supplier_email=supplier_email;
    }

    public void setSupplier_id(int supplier_id)
    {
        this.supplier_id=supplier_id;
    }
    public void setSupplier_name(String supplier_name)
    {
        this.supplier_name=supplier_name;
    }
    public void setSupplier_address(String supplier_address)
    {
        this.supplier_address=supplier_address;
    }
    public void setSupplier_email(String supplier_email)
    {
        this.supplier_email=supplier_email;
    }

    public int getSupplier_id()
    {
        return supplier_id;
    }
    public String getSupplier_name()
    {
        return supplier_name;
    }
    public String getSupplier_address()
    {
        return supplier_address;
    }
    public String getSupplier_email()
    {
        return supplier_email;
    }



}
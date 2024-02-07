package Models.components;

import java.sql.ResultSet;
import Models.database.*;

public class Materiel extends BaseModel<Materiel> {
    String id;
    String intitule;
    double unitPrice;

    public Materiel()
    { }

    public Materiel(String id, String intitule, double price) 
        throws Exception
    {
       this.setId(id);
       this.setIntitule(intitule);
       this.setUnitPrice(price);
    }

    public Materiel getSpecificMateriel(String id) 
        throws Exception
    {
        Materiel[] allmateriel = this.getAll(Materiel.class, null);

        for (Materiel materiel : allmateriel) {
            if (materiel.getId().equals(id))
            { return materiel; }
        }

        return null;
    }

    // INSERT MATERIEL
    public void insertMateriel() {
        String query = "insert into Materiel values (default,'"+this.getIntitule()+"', "+this.getUnitPrice()+")";

        FunctionDB.execute(query, null);
    }     

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Materiel mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String intitule = result.getString(2);
        double price = result.getDouble(3);

        return new Materiel(id, intitule, price);
    }

    public void setIntitule(String intitule) 
        throws Exception
    {
        if (intitule.length() == 0 || intitule == null)
        { throw new Exception("Invalid intitule !"); }

        this.intitule = intitule; 
    }

    public void setId(String id) 
     throws Exception
    {
        if (id.length() == 0 || id == null)
        { throw new Exception("Invalid id !"); }
         
        this.id = id; 
    }

    public String getIntitule() 
    { return intitule; }

    public String getId() 
    { return id; }

    public double getUnitPrice()
    { return this.unitPrice; }

    public void setUnitPrice(double prix) 
        throws Exception 
    {
        if (prix <= 0)
        { throw new Exception("Invalid Price !"); }

        this.unitPrice = prix;
    }
}
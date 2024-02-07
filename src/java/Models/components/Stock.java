package Models.components;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;

import Models.database.BaseModel;
import Models.database.Database;
import Models.database.FunctionDB;

public class Stock extends BaseModel<Stock>{
    String id;
    Materiel materiel;
    int quantity;

    public Stock()
    { }

    public Stock(String id, Materiel mat, int quantity) 
        throws Exception
    {
        this.setId(id);
        this.setMateriel(mat);
        this.setQuantity(quantity);
    }
    
    public int getQteMateriel(String idMateriel) throws Exception {
        String sql = "SELECT qte_stock FROM v_qte_materiel_stock WHERE id_materiel = '"+idMateriel+"'";
        System.out.println(sql);
        BigDecimal res = (BigDecimal) FunctionDB.fetch(sql, null).get(0);

        return res.intValue(); // Convertir en int
    }

    public void insertStock(String date) {
        String query = "INSERT INTO MouvementStock VALUES (DEFAULT, '"+this.getMateriel().getId()+"', "+this.getQuantity()+", 'IN', '"+java.sql.Date.valueOf(date)+"')";

        FunctionDB.execute(query, null);
    } 

    @Override
    protected Stock mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String idMateriel = result.getString(2);
        int quantity = result.getInt(3);

        Materiel m = new Materiel();
        Materiel mat = m.getSpecificMateriel(idMateriel);

        return new Stock(id, mat, quantity);
    }

    public Stock[] getAllStock(Connection con)
        throws Exception
    {
        Vector<Stock> list= new Vector<Stock>();

        boolean valid = true;
        Statement state = null;
        ResultSet result = null;

        Materiel m = new Materiel();

        try {
            if(con == null) {
                con = Database.connectionPostgres();
                valid = false;
            }

            String sql = "SELECT * FROM v_qte_materiel_stock" ;
            state = con.createStatement();
            result = state.executeQuery(sql);

            while(result.next()) {
                String id = "MVT";
                String idMateriel = result.getString(1);
                int quantity = result.getInt(2);
        
                Materiel mat = m.getSpecificMateriel(idMateriel);
        
                Stock item = new Stock(id, mat, quantity);
                list.add(item);
            }
        } 
        
        catch (Exception e) 
        { e.printStackTrace(); }
        
        finally {
            try {
                if (state != null) state.close(); 
                if (result != null ) result.close(); 
                if (valid == false || con !=null) con.close(); 
            } 
            
            catch (Exception e) 
            { e.printStackTrace(); }
        }

        Stock[] items = (Stock[]) Array.newInstance(Stock.class, list.size());
        list.toArray(items);

        return items;
    }


    public int getQuantity()
    { return this.quantity; }

    public void setQuantity(int q) 
        throws Exception
    {
        if (q < 0)
        { throw new Exception("Invalid Quantity !"); }

        this.quantity = q;
    }

    public void setMateriel(Materiel mat)
        throws Exception 
    {
        if (mat == null)
        { throw new Exception("Materiel can't be null !"); }

        this.materiel = mat;
    }

    public Materiel getMateriel()
    { return this.materiel; }

    public void setId(String id) 
        throws Exception
    {
       if (id.length() == 0 || id == null)
       { throw new Exception("Invalid id !"); }
        
       this.id = id; 
    }

    public String getId() 
    { return id; }


    public static void main(String[] args) {
        try {
            Stock s = new Stock();
            System.out.println(s.getQteMateriel("MAT0002")); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
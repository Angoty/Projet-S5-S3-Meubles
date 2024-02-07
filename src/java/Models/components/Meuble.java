package Models.components;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Models.components.*;
import Models.database.BaseModel;
import Models.database.Database;
import Models.database.FunctionDB;

public class Meuble extends BaseModel<Meuble>{
    String id;
    String intitule;
    Volume volume;
    Style style;

    double benef;
    
    public Meuble()
    { }

    public Meuble(String id, String intitule, Volume vol, Style st) 
        throws Exception
    {
        this.setId(id);
        this.setIntitule(intitule);
        this.setStyle(st);
        this.setVolume(vol);
    }

    public static double getPrixAchat(String idMeuble) 
        throws Exception 
    {
        String query = "SELECT prix_achat FROM MeublePrixAchat WHERE id_meuble ='"+idMeuble+"'";

        System.out.println(query);

        BigDecimal price = (BigDecimal) FunctionDB.fetch(query, null).get(0);
        return price.doubleValue(); 
    }

    // INSERT Meuble
    public static void insertMeublePrixAchat(String idMeuble, String volume, double prix) {
        String query = "insert into MeublePrixAchat values ('"+idMeuble+"', '"+volume+"', "+prix+")";
        
        FunctionDB.execute(query, null);
    } 


    // INSERT Meuble
    public void insertMeuble() {
        String query = "insert into Meuble values (default, '"+this.getIntitule()+"', '"+this.getStyle().getId()+"', '"+this.getVolume().getId()+"', 0)";        
        FunctionDB.execute(query, null);
    }     

    // GET SPECIFIC MEUBLE
    public Meuble getSpecificMeuble(String id) 
        throws Exception
    {
        Meuble[] allMeuble = this.getAll(Meuble.class, null);

        for (Meuble meuble : allMeuble) {
            if (meuble.getId().equals(id))
            { return meuble; }
        }

        return null;
    }

    // GET ALL MATERIELS FOR A MEUBLE
    public Materiel[] getAllMateriel() 
        throws Exception
    {
        Materiel mat = new Materiel();
        Style s = new Style();
        Vector<Materiel> vectMateriels = new Vector<Materiel>();

        String[] idMateriel = s.getAllMaterielsFromStyle(this.getStyle().getId());
        Materiel[] materiels = mat.getAll(Materiel.class, null);
        
        for (Materiel materiel : materiels) {
            for (String id : idMateriel) {
                if (materiel.getId().equals(id))
                { vectMateriels.add(materiel); }
            }
        }

        Materiel[] listMateriel = (Materiel[]) Array.newInstance(Materiel.class, vectMateriels.size());
        vectMateriels.toArray(listMateriel);

        return listMateriel;
    }

    // GET MEUBLE QUANTITY MATERIEL 
    public static Map<String, Object> getMeubleQteMateriel(String idMateriel, Connection con) {
        Statement statement = null;
        ResultSet set = null;

        String query = "select * from MeubleQuantityMateriel where id_materiel = '"+idMateriel+"'";
        System.out.println(query);

        Map<String, Object> rowData = new HashMap<>();

        try {
            if (con == null) 
            { con = Database.connectionPostgres(); }
            
            statement = con.createStatement();
            set = statement.executeQuery(query);

            while (set.next()) {
                rowData.put("id_meuble_qte_materiel", set.getString("id_meuble_qte_materiel"));
                rowData.put("id_meuble", set.getString("id_meuble"));
                rowData.put("id_volume", set.getString("id_volume"));
                rowData.put("id_materiel", set.getString("id_materiel"));
                rowData.put("qte", set.getInt("qte"));
            }
            
            return rowData;
        } 
        
        catch (Exception e) 
        { e.printStackTrace(); }

        finally {
            try {
                if (con != null)  con.close();
                if (statement != null) statement.close();
                if (set != null) set.close();
            } 
            catch (Exception ex) 
            { ex.printStackTrace(); }
        }

        return rowData;
    }

    @Override
    protected Meuble mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String intitule = result.getString(2);
        String idStyle = result.getString(3);
        String idVolume = result.getString(4);

        Volume v = new Volume();
        Style s = new Style();
        Meuble meuble = new Meuble(id, intitule, v.getSpecificVolume(idVolume), s.getSpecificStyle(idStyle));

        return meuble;
    }

    public Volume getVolume() 
    { return volume; }

    public void setVolume(Volume vol) 
        throws Exception
    {
        if (vol == null)
        { throw new Exception("Volume can't be null"); } 

        this.volume = vol; 
    }

    public Style getStyle() 
    { return style; }

    public void setStyle(Style style) 
        throws Exception
    {
        if (style == null)
        { throw new Exception("Style can't be null"); } 

        this.style = style; 
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

    public void setBenef(double benef) 
    { this.benef = benef; }

    public String getIntitule() 
    { return intitule; }

    public String getId() 
    { return id; }

    public double getBenef() 
    { return benef; }
}
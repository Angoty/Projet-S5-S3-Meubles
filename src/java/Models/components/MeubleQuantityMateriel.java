package Models.components;

import Models.database.BaseModel;
import Models.database.Database;
import Models.database.FunctionDB;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.*;

public class MeubleQuantityMateriel extends BaseModel<MeubleQuantityMateriel> {
    private String idMeubleQteMateriel;
    private String idMeuble;
    private String idVolume;
    private String idMateriel;
    private int qte;

    public MeubleQuantityMateriel() 
    { }

    public MeubleQuantityMateriel(String idMeubleQteMateriel, String idMeuble, String idVolume, String idMateriel, int qte) 
        throws Exception
    {
        this.setIdMeubleQteMateriel(idMeubleQteMateriel);
        this.setIdMeuble(idMeuble);
        this.setIdVolume(idVolume);
        this.setIdMateriel(idMateriel);
        this.setQte(qte);
    }

    // QTE MATERIEL
    public MeubleQuantityMateriel[] getMeubleMaterielQte(String idMeuble)
        throws Exception
    {
        String query = "SELECT id_meuble_qte_materiel FROM v_meuble_qte_materiel WHERE id_meuble = '"+idMeuble+"'";
        query = String.format(query, idMeuble);

        Vector res = FunctionDB.fetch(query, null);
        Vector<MeubleQuantityMateriel> result = new Vector<MeubleQuantityMateriel>();
        
        MeubleQuantityMateriel[] all = this.getAll(MeubleQuantityMateriel.class, null);

        for (MeubleQuantityMateriel meu : all) {
            for (int i = 0; i < res.size(); i++) {
                String id = (String) res.get(i);
                if (meu.getIdMeubleQteMateriel().equals(id))
                { result.add(meu); }
            }
        }

        MeubleQuantityMateriel[] list = (MeubleQuantityMateriel[]) Array.newInstance(MeubleQuantityMateriel.class, result.size());
        result.toArray(list);

        return list;
    }

    // ACHAT MEUBLE
    public static void insertAchatMeuble(String cl, String id, int qte, String dt) {
        String sql = " INSERT INTO AchatMeuble VALUES ('"+cl+"', '"+id+"', "+qte+", '"+dt+"')"; 
        FunctionDB.execute(sql, null);
    }

    // CONSTRUCT MEUBLE
    public static void constructMeuble(String idMeuble, int qte, String date) 
        throws Exception 
    {
        MeubleQuantityMateriel m = new MeubleQuantityMateriel();
        Materiel mat = new Materiel();
        MeubleQuantityMateriel[] meubles = m.getMeubleMaterielQte(idMeuble);
    
        Stock stock = new Stock();
        Stock[] allStock = stock.getAll(Stock.class, null);
    
        Vector<Materiel> errorMaterials = new Vector<Materiel>();
    
        for (MeubleQuantityMateriel meu : meubles) {
            int qteOut = meu.getQte() * qte;
            int qteStock = stock.getQteMateriel(meu.getIdMateriel());
            Materiel materiel = mat.getSpecificMateriel(meu.getIdMateriel());
    
            if (qteOut > qteStock) 
            { errorMaterials.add(materiel); } 
            
            else {
                String query = "INSERT INTO MouvementStock VALUES (DEFAULT, '"+meu.getIdMateriel()+"', "+qteOut+", 'OUT', '"+java.sql.Date.valueOf(date)+"')";    
                FunctionDB.execute(query, null);
            }
        }
    
        if (!errorMaterials.isEmpty()) {
            String message = "Manque en stock : <br>";
            
            for (MeubleQuantityMateriel meu : meubles) {
                Materiel materiel = mat.getSpecificMateriel(meu.getIdMateriel());

                for (Materiel mater : errorMaterials) {
                    if (mater.getId().equals(materiel.getId())) {
                        int qteOut = meu.getQte() * qte;
                        int qteStock = stock.getQteMateriel(meu.getIdMateriel());
            
                        message += "Materiel: " + materiel.getIntitule() + "<br>";
                        message += "Quantite necessaire: " + qteOut + "<br>";
                        message += "Quantite en stock: " + qteStock + "<br>";
                    }
                }
            }
    
            throw new Exception(message);
        }
    }
    

    // PRICE MEUBLE 
    public double getPriceMeuble() 
        throws Exception 
    {
        String query = "SELECT total_material_price FROM v_price_meuble WHERE id_meuble ='"+this.getIdMeuble()+"'";

        BigDecimal price = (BigDecimal) FunctionDB.fetch(query, null).get(0);
        return price.doubleValue(); 
    }

    public static double getPriceMeuble(String id) 
        throws Exception 
    {
        String query = "SELECT total_material_price FROM v_price_meuble WHERE id_meuble ='"+id+"'";
        BigDecimal price = (BigDecimal) FunctionDB.fetch(query, null).get(0);
        return price.doubleValue(); 
    }


    // INSERT MEUBLE QUANTITY MATERIEL
    public static void insertMeubleQteMateriel(String idMeuble, String idVolume, String[] idMateriel, int[] qte) 
        throws Exception
    {
        for (int i = 0; i < idMateriel.length; i++) {
            String query = "insert into MeubleQuantityMateriel values (DEFAULT, '"+idMeuble+"', '"+idVolume+"', '"+idMateriel[i]+"', "+qte[i]+")";

            FunctionDB.execute(query, null);
        }
    }

    // GET BY ID MATERIEL 
    public MeubleQuantityMateriel[] getByIdMateriel(String idMateriel) 
        throws Exception
    {
        MeubleQuantityMateriel[] allMeubleQuantityMateriel = this.getAll(MeubleQuantityMateriel.class, null);
        Vector<MeubleQuantityMateriel> result = new Vector<MeubleQuantityMateriel>();

        for (MeubleQuantityMateriel meubleQuantityMateriel : allMeubleQuantityMateriel) {
            if (meubleQuantityMateriel.getIdMateriel().equals(idMateriel))
            { result.add(meubleQuantityMateriel); }
        }

        MeubleQuantityMateriel[] list = (MeubleQuantityMateriel[]) Array.newInstance(MeubleQuantityMateriel.class, result.size());
        result.toArray(list);

        return list;
    }

    // GET BY ID MQM
    public MeubleQuantityMateriel[] getByIdMeubleQuantityMateriel(String id) 
        throws Exception
    {
        MeubleQuantityMateriel[] allMeubleQuantityMateriel = this.getAll(MeubleQuantityMateriel.class, null);
        Vector<MeubleQuantityMateriel> result = new Vector<MeubleQuantityMateriel>();

        for (MeubleQuantityMateriel meubleQuantityMateriel : allMeubleQuantityMateriel) {
            if (meubleQuantityMateriel.getIdMeubleQteMateriel().equals(id))
            { result.add(meubleQuantityMateriel); }
        }

        MeubleQuantityMateriel[] list = (MeubleQuantityMateriel[]) Array.newInstance(MeubleQuantityMateriel.class, result.size());
        result.toArray(list);

        return list;
    }

    // GET INTITULE BY ID
    public String getIntituleById(String id, String model) {
        String query = "";

        switch (model) {
            case "meuble":
                query += "select intitule from Meuble where id_meuble ='"+id+"'";
                break;

            case "materiel":
                query += "select intitule from Materiel where id_materiel ='"+id+"'";
                break;

            case "style":
                query += "select intitule from Style where id_style ='"+id+"'";
                break;

            case "volume":
                query += "select intitule from Volume where id_volume ='"+id+"'";
                break;
        
            default:
                break;
        }

        String intitule = (String) FunctionDB.fetch(query, null).get(0);
        return intitule;
    }

    // FILTER
    public MeubleQuantityMateriel[] getInstancesInPriceRange(double minPrice, double maxPrice) 
            throws Exception 
    {
        MeubleQuantityMateriel[] allInstances = this.getAll(MeubleQuantityMateriel.class, null);
        Vector<MeubleQuantityMateriel> result = new Vector<>();

        for (MeubleQuantityMateriel instance : allInstances) {
            double price = instance.getPriceMeuble();
            if (price >= minPrice && price <= maxPrice) {
                result.add(instance);
            }
        }

        // Remove duplicates based on idMeuble
        Set<String> seenIds = new HashSet<>();
        Iterator<MeubleQuantityMateriel> iterator = result.iterator();
        while (iterator.hasNext()) {
            MeubleQuantityMateriel instance = iterator.next();
            if (!seenIds.add(instance.getIdMeuble())) 
            { iterator.remove(); }
        }

        MeubleQuantityMateriel[] filteredInstances = new MeubleQuantityMateriel[result.size()];
        result.toArray(filteredInstances);

        return filteredInstances;
    }
   


    @Override
    protected MeubleQuantityMateriel mapRow(ResultSet result) 
        throws Exception 
    {
        String idMeubleQteMateriel = result.getString("id_meuble_qte_materiel");
        String idMeuble = result.getString("id_meuble");
        String idVolume = result.getString("id_volume");
        String idMateriel = result.getString("id_materiel");
        int qte = result.getInt("qte");

        return new MeubleQuantityMateriel(idMeubleQteMateriel, idMeuble, idVolume, idMateriel, qte);
    }

    public String getIdMeubleQteMateriel() 
    { return idMeubleQteMateriel;}

    public void setIdMeubleQteMateriel(String idMeubleQteMateriel) 
        throws Exception
    { 
        if (idMeubleQteMateriel.length() == 0 || idMeubleQteMateriel == null)
        { throw new Exception("Invalid idMeubleQteMateriel !"); }

        this.idMeubleQteMateriel = idMeubleQteMateriel;
    }

    public String getIdMeuble() 
    { return idMeuble;}

    public void setIdMeuble(String idMeuble) 
        throws Exception
    { 
        if (idMeuble.length() == 0 || idMeuble == null)
        { throw new Exception("Invalid idMeuble !"); }

        this.idMeuble = idMeuble;
    }

    public String getIdVolume() 
    { return idVolume;}

    public void setIdVolume(String idVolume) 
        throws Exception
    {
        if (idVolume.length() == 0 || idVolume == null)
        { throw new Exception("Invalid idVolume !"); } 
        this.idVolume = idVolume;
    }

    public String getIdMateriel() 
    { return idMateriel;}

    public void setIdMateriel(String idMateriel) 
        throws Exception
    {
        if (idMateriel.length() == 0 || idMateriel == null)
        { throw new Exception("Invalid idMateriel !"); } 
        this.idMateriel = idMateriel;
    }

    public int getQte() 
    { return qte;}

    public void setQte(int qte) 
        throws Exception
    {
        if (qte <= 0)
        { throw new Exception("Invalid quantity"); } 
        this.qte = qte;
    }

    public static void main(String[] args) {
        try {
            MeubleQuantityMateriel m = new MeubleQuantityMateriel();
            MeubleQuantityMateriel[] meu = m.getAll(MeubleQuantityMateriel.class, null);

            for (MeubleQuantityMateriel meub : meu)
            { System.out.println(meub.getPriceMeuble()); }
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }      
    }
}
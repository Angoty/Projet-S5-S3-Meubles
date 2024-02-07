package Models.components;

import java.math.BigInteger;
import java.sql.ResultSet;

import Models.database.BaseModel;
import Models.database.FunctionDB;

public class Poste extends BaseModel<Poste> {
    String id;
    String typePoste;

    public Poste()
    { }

    public Poste(String i, String t) 
        throws Exception
    {
        this.setId(i);
        this.setTypePoste(t);
    }


    public Poste getSpecificPoste(String id) 
        throws Exception
    {
        Poste[] allPoste = this.getAll(Poste.class, null);

        for (Poste poste : allPoste) {
            System.out.println(poste.getId());
            if (poste.getId().equals(id))
            { return poste; }
        }

        return null;
    }

    // INSERT Poste
    public void insertPoste() {
        String query = "insert into Poste values (default, '"+this.getTypePoste()+"')";

        FunctionDB.execute(query, null);
    }   

    
    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Poste mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String typePoste = result.getString(2);

        return new Poste(id, typePoste);
    }

    public static int getPosteId(String idPoste) {
        String sql = " SELECT id FROM ExperiencePoste WHERE id_poste ='"+idPoste+"'";
        System.out.println(sql);
        
        Integer id = (Integer) FunctionDB.fetch(sql, null).get(0);

        return id.intValue();
    }

    public static int getDelaiFromDateEmbauche(String idPoste) {
        int id = Poste.getPosteId(idPoste);
        String sql = " SELECT SUM(delai)  FROM ExperiencePoste  WHERE id <"+(id+1);
        Long res = (Long) FunctionDB.fetch(sql, null).get(0);

        return res.intValue();
    }

    public static int getMultiplicateur(String idPoste) {
        String sql = " SELECT multiplicateur FROM SalaryPoste WHERE id_poste = '"+idPoste+"'";

        Integer id = (Integer) FunctionDB.fetch(sql, null).get(0);
        return id.intValue();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) 
        throws Exception
    {
        if (id == null || id.length() == 0 || id == "")
        { throw new Exception("Invalid value !"); }

        this.id = id;
    }
    public String getTypePoste() {
        return typePoste;
    }
    public void setTypePoste(String typePoste) 
        throws Exception
    {
        if (typePoste == null || typePoste.length() == 0 || typePoste == "")
        { throw new Exception("Invalid value !"); }
        
        this.typePoste = typePoste;
    }
}
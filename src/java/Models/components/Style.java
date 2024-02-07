package Models.components;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.Vector;

import Models.database.*;

public class Style extends BaseModel<Style> {
    String id;
    String intitule;

    public Style()
    { }

    public Style(String id, String intitule) 
        throws Exception
    {
       this.setId(id);
       this.setIntitule(intitule);
    }

    // INSERT Style
    public void insertStyle() {
        String query = "insert into Style values (default, '"+this.getIntitule()+"')";
        System.out.println(query);
        FunctionDB.execute(query, null);
    }     

    // GET ALL STYLE INTITULE
    public static Vector<String> getAllStyleIntitule() {
        String sql = " SELECT intitule FROM Style ";

        Vector res = FunctionDB.fetch(sql, null);
        Vector<String> vector = new Vector<String>();

        for (int i = 0; i < res.size(); i++) {
            String style = (String) res.get(i);
            vector.add(style);
        }

        return vector;
    }

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Style mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String intitule = result.getString(2);

        return new Style(id, intitule);
    }

    // GET ALL MATERIELS OF A STYLE
    public String[] getAllMaterielsFromStyle(String idStyle) 
        throws Exception
    {
        Materiel mat = new Materiel();
        Materiel[] allMateriels = mat.getAll(Materiel.class, null);

        String query = "select id_materiel from StyleMateriel where id_style ='"+idStyle+"'";

        Vector vect = FunctionDB.fetch(query, null);
        Vector<String> result = new Vector<String>();

        for (int i = 0; i < allMateriels.length; i++) { 
            if (vect.contains(allMateriels[i].getId())) 
            { result.add(allMateriels[i].getId()); }
        }

        String[] items = (String[]) Array.newInstance(String.class, result.size());
        result.toArray(items);

        return items;
    }

    // GET A SPECIFIC STYLE 
    public Style getSpecificStyle(String id) 
        throws Exception
    {
        Style[] allStyle = this.getAll(Style.class, null);

        for (Style style : allStyle) {
            if (style.getId().equals(id))
            { return style; }
        }

        return null;
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

    public String getId(String intitule) {
        String query = "select id_style from Style where intitule ='"+intitule+"'";
        System.out.println(query);

        String id = (String) FunctionDB.fetch(query, null).get(0);
        return id;
    }

    public String getIntitule() 
    { return intitule; }

    public String getId() 
    { return id; }
}
package Models.components;

import java.sql.ResultSet;
import Models.database.*;

public class Volume extends BaseModel<Volume> {
    String id;
    String intitule;

    public Volume()
    { }

    public Volume(String id, String intitule) 
        throws Exception
    {
       this.setId(id);
       this.setIntitule(intitule);
    }

    // INSERT Volume
    public void insertVolume() {
        String query = "insert into Volume values (default, '"+this.getIntitule()+"')";

        System.out.println(query);
        FunctionDB.execute(query, null);
    }     

     // GET A SPECIFIC VOLUME 
    public Volume getSpecificVolume(String id) 
        throws Exception
    {
        Volume[] allVolume = this.getAll(Volume.class, null);

        for (Volume volume : allVolume) {
            if (volume.getId().equals(id))
            { return volume; }
        }

        return null;
    }

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Volume mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String intitule = result.getString(2);

        return new Volume(id, intitule);
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
}
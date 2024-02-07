package Models.components;

import java.sql.ResultSet;
import Models.database.*;

public class Gender extends BaseModel<Gender> {
    String id;
    String intitule;

    public Gender()
    { }

    public Gender(String id, String intitule) 
        throws Exception
    {
       this.setId(id);
       this.setIntitule(intitule);
    }

    public Gender getSpecificGender(String id) 
        throws Exception
    {
        Gender[] allGender = this.getAll(Gender.class, null);

        for (Gender Gender : allGender) {
            if (Gender.getId().equals(id))
            { return Gender; }
        }

        return null;
    }

    // INSERT Gender
    public void insertGender() {
        String query = "insert into Gender values (default, '"+this.getIntitule()+"')";
        FunctionDB.execute(query, null);
    }     

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Gender mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String intitule = result.getString(2);

        return new Gender(id, intitule);
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
package Models.components;

import java.sql.ResultSet;
import Models.database.*;

public class Worker extends BaseModel<Worker> {
    String id;
    String intitule;

    public Worker()
    { }

    public Worker(String id, String intitule) 
        throws Exception
    {
       this.setId(id);
       this.setIntitule(intitule);
    }

    public Worker getSpecificWorker(String id) 
        throws Exception
    {
        Worker[] allWorker = this.getAll(Worker.class, null);

        for (Worker worker : allWorker) {
            if (worker.getId().equals(id))
            { return worker; }
        }

        return null;
    }

    // INSERT Worker
    public void insertWorker() {
        String query = "insert into Worker values (default, '"+this.getIntitule()+"')";
        FunctionDB.execute(query, null);
    }     

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Worker mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String intitule = result.getString(2);

        return new Worker(id, intitule);
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
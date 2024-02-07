package Models.components;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import Models.database.*;

public class Client extends BaseModel<Client> {
    String id;
    String intitule;
    String gender;
    double budget;


    public Client()
    { }

    public Client(String id, String intitule, String g, double d) 
        throws Exception
    {       
        System.out.println("constr bu : " + d);
        this.setId(id);
        this.setIntitule(intitule);
        this.setBudget(d);
        this.setGender(g);
    }

    // GET ALL GENDER
    public static Vector<String> getAllGender() {
        String sql = " SELECT intitule FROM Gender";
        
        Vector vector = FunctionDB.fetch(sql, null);
        Vector<String> res = new Vector<String>();

        for (int i = 0; i < vector.size(); i++) {
            String gender = (String) vector.get(i);
            res.add(gender);
        }

        return res;
    }

    public Client getSpecificClient(String id) 
        throws Exception
    {
        Client[] allClient = this.getAll(Client.class, null);

        for (Client client : allClient) {
            if (client.getId().equals(id))
            { return client; }
        }

        return null;
    }


    // TRANSACTION 
    public void transaction (double totalPrice) 
        throws Exception 
    {
        String sql = " SELECT budget FROM Client WHERE id_client='"+this.getId()+"'";
        BigDecimal bu = (BigDecimal) FunctionDB.fetch(sql, null).get(0);

        if (bu.doubleValue() >= totalPrice) {
            double newBudget = bu.doubleValue() - totalPrice;
            String sql2 = " UPDATE Client SET budget ="+newBudget+" WHERE id_client ='"+this.getId()+"'";

            FunctionDB.execute(sql2, null);
        }

        else 
        { throw new Exception("The client has no enough money !"); }
    }

    // INSERT Client
    public void insertClient() {
        String query = "insert into Client values (default, '"+this.getGender()+"', '"+this.getIntitule()+"', "+this.getBudget()+")";
        FunctionDB.execute(query, null);
    }     

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Client mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String gender = result.getString(2);
        String inti = result.getString(3);
        double bu = result.getDouble(4);

        System.out.println("id cl map " + id);
        System.out.println("gender map " + gender);
        System.out.println("inti map " + inti);
        System.out.println("bu map " + bu);

        return new Client(id, inti, gender, bu);
    }

    public String getId() 
    { return id; }

    public void setId(String id)
        throws Exception 
    { 
        if (id == null || id.length() == 0 || id == "")
        { throw new Exception("Invalid value !"); }

        this.id = id; 
    }

    public String getIntitule() 
    { return intitule; }

    public void setIntitule(String intitule)
        throws Exception 
    { 
        if (intitule == null || intitule.length() == 0 || intitule == "")
        { throw new Exception("Invalid value !"); }

        this.intitule = intitule; 
    }

    public String getGender() 
    { return gender; }

    public void setGender(String gender)
        throws Exception 
    { 
        if (gender == null || gender.length() == 0 || gender == "")
        { throw new Exception("Invalid value !"); }

        this.gender = gender; 
    }

    public double getBudget() 
    { return budget; }

    public void setBudget(double budget)
        throws Exception 
    { 
        if (budget <= 0)
        { throw new Exception("Invalid value of ! : " + this.getId() + " " + budget); }

        this.budget = budget; 
    }
}
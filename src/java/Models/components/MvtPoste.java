package Models.components;

import java.sql.Date;
import java.sql.ResultSet;
import Models.database.BaseModel;
import Models.database.FunctionDB;

public class MvtPoste extends BaseModel<MvtPoste> {
    String idPerson;
    String idNewPost;
    Date newDate;
    double newSalary;

    public MvtPoste() 
    { }

    public MvtPoste(String idPerson, String idNewPost, Date newDate, double newSalary) 
        throws Exception
    {
        this.setIdPerson(idPerson);
        this.setIdNewPost(idNewPost);
        this.setNewDate(newDate);
        this.setNewSalary(newSalary);
    }

    // INSERT MvtPoste
    public void insertMvtPoste() {
        String query = "INSERT INTO MvtPoste VALUES ('"+this.getIdPerson()+"', '"+this.getIdNewPost()+"', '"+this.getNewDate()+"', "+this.getNewSalary()+")";
        System.out.println("query MVT Poste : " + query);
        FunctionDB.execute(query, null);
    }

    // GET PERSON POSTE 
    public static Poste getPersonCurrentPoste(Person person) 
        throws Exception
    {
        String sql = " SELECT id_new_post FROM MvtPoste WHERE id_person ='"+person.getId()+"'";
        if (FunctionDB.fetch(sql, null).size() == 0)
        { return new Poste().getSpecificPoste("POS0001"); }

        else {
            String posteId = (String) FunctionDB.fetch(sql, null).get(0);
            return new Poste().getSpecificPoste(posteId);
        }
    } 

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected MvtPoste mapRow(ResultSet result) throws Exception {
        // Map row logic
        String idPerson = result.getString(1);
        String idNewPost = result.getString(2);
        Date newDate = result.getDate(3);
        double newSalary = result.getDouble(4);

        MvtPoste mvtPoste = new MvtPoste(idPerson, idNewPost, newDate, newSalary);
        return mvtPoste;
    }

    public String getIdPerson() 
    { return idPerson;}

    public void setIdPerson(String idPerson) 
        throws Exception
    {
        if (idPerson == null || idPerson.length() == 0 || idPerson == "")
        { throw new Exception("Invalid value !"); }

        this.idPerson = idPerson;
    }

    public String getIdNewPost() 
    { return idNewPost;}

    public void setIdNewPost(String idNewPost) 
        throws Exception
    {
        if (idNewPost == null || idNewPost.length() == 0 || idNewPost == "")
        { throw new Exception("Invalid value !"); }

        this.idNewPost = idNewPost;
    }

    public Date getNewDate() 
    { return newDate;}

    public void setNewDate(Date newDate) 
        throws Exception
    {
        if (newDate == null)
        { throw new Exception("Invalid value !"); }

        this.newDate = newDate;
    }

    public double getNewSalary() 
    { return newSalary;}

    public void setNewSalary(double newSalary) 
        throws Exception
    {
        if (newSalary <= 0)
        { throw new Exception("Invalid value !"); }

        this.newSalary = newSalary;
    }
}
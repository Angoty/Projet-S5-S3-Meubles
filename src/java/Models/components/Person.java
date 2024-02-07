package Models.components;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import Models.database.*;

public class Person extends BaseModel<Person> {
    String id;
    String intitule;
    Poste poste;
    Date dateEmbauche;
    double salary;

   

    public Person()
    { }

    public Person(String id, String intitule, Poste p, Date dt, double s) 
        throws Exception
    {
       this.setId(id);
       this.setIntitule(intitule);
       this.setPoste(p);
       this.setSalary(s);
       this.setDateEmbauche(dt);
    }

    public Person getSpecificPerson(String id) 
        throws Exception
    {
        Person[] allPerson = this.getAll(Person.class, null);

        for (Person person : allPerson) {
            if (person.getId().equals(id))
            { return person; }
        }

        return null;
    }

    public void updatePoste(Person person, Date currentDate)  
        throws Exception 
    {
        Poste currentPoste = MvtPoste.getPersonCurrentPoste(person);

        if (!currentPoste.getId().equals("POS0003")) {
            long diffInMilliseconds = currentDate.getTime() - person.getDateEmbauche().getTime();
            long diffInYears = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds) / 365;
            
            if (diffInYears < Poste.getDelaiFromDateEmbauche("POS0002") && currentDate.after(person.getDateEmbauche())) {
                Poste seniorPoste = new Poste().getSpecificPoste("POS0001");
                person.setPoste(seniorPoste);

                int multi = Poste.getMultiplicateur("POS0001");
                person.setSalary(person.getSalary() * multi);

                MvtPoste mvtPoste = new MvtPoste(person.getId(), seniorPoste.getId(), currentDate, person.getSalary());
                mvtPoste.insertMvtPoste();
            }

            else if (diffInYears >= Poste.getDelaiFromDateEmbauche("POS0002") && diffInYears < Poste.getDelaiFromDateEmbauche("POS0003")) {
                Poste seniorPoste = new Poste().getSpecificPoste("POS0002");
                person.setPoste(seniorPoste);

                int multi = Poste.getMultiplicateur("POS0002");
                person.setSalary(person.getSalary() * multi);

                MvtPoste mvtPoste = new MvtPoste(person.getId(), seniorPoste.getId(), currentDate, person.getSalary());
                mvtPoste.insertMvtPoste();
            }

            else if (diffInYears >= Poste.getDelaiFromDateEmbauche("POS0003")) {  
                Poste seniorPoste = new Poste().getSpecificPoste("POS0003");
                person.setPoste(seniorPoste);

                int multi = Poste.getMultiplicateur("POS0003");
                person.setSalary(person.getSalary() * multi);

                MvtPoste mvtPoste = new MvtPoste(person.getId(), seniorPoste.getId(), currentDate, person.getSalary());
                mvtPoste.insertMvtPoste();
            }

            else {
                System.out.println("Another diff : " + diffInYears + " for : " + person.getIntitule());
            }

            System.out.println("DIff 1 : " + Poste.getDelaiFromDateEmbauche("POS0002"));
            System.out.println("DIff 2 : " + Poste.getDelaiFromDateEmbauche("POS0003"));
        }
    }
 
    // INSERT Person
    public void insertPerson() {
        String query = "insert into Person values (default, '"+this.getPoste().getId()+"', '"+this.getIntitule()+"', '"+this.getDateEmbauche()+"', "+this.getSalary()+")";

        FunctionDB.execute(query, null);
    }     

    // ABSTRACT METHOD MAP ROW 
    @Override
    protected Person mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String idPoste = result.getString(2);
        String intitule = result.getString(3);
        Date dt = result.getDate(4);
        double sal = result.getDouble(5);

        Poste p = new Poste();
        Poste poste = p.getSpecificPoste(idPoste);
        
        return new Person(id, intitule, poste, dt, sal);
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

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) 
        throws Exception
    {
        if (poste == null)
        { throw new Exception("Invalid Value !"); }
        this.poste = poste;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) 
        throws Exception
    {
        if (dateEmbauche == null)
        { throw new Exception("Invalid Value !"); }
        this.dateEmbauche = dateEmbauche;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) 
        throws Exception
    {
        if (salary <= 0)
        { throw new Exception("Invalid Value !"); }
        this.salary = salary;
    }
}
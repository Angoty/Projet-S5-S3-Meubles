package Models.components;

import Models.database.FunctionDB;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Vector;

import Models.database.BaseModel;


public class MeubleWorker extends BaseModel<MeubleWorker> {
    String id;
    Meuble meuble;
    Worker worker;
    Volume volume;
    int number;
    double salary;


    public MeubleWorker()
    { }

    public MeubleWorker(String id, Meuble meu, Worker w, Volume vol, int num, double sal) 
        throws Exception
    {
        this.setId(id);
        this.setMeuble(meu);
        this.setSalary(sal);
        this.setVolume(vol);
        this.setWorker(w);
        this.setNumber(num);
    }

    public void insertMeubleWorker(String idMeuble, String[] idWorker, double[] sal, int[] number) {
        for (int i = 0; i < idWorker.length; i++) {
            String sql = "INSERT INTO MeubleWorker VALUES (DEFAULT, '"+idMeuble+"', '"+idWorker[i]+"','VOL0001', "+number[i]+","+ sal[i]+") ";
            String sql2 = "INSERT INTO MeubleWorker VALUES (DEFAULT, '"+idMeuble+"', '"+idWorker[i]+"','VOL0002', "+(number[i]*2)+", "+ sal[i]+") ";

            sql = String.format(sql, idMeuble, idWorker[i], number[i], sal[i]);
            sql2 = String.format(sql2, idMeuble, idWorker[i], (number[i] * 2), sal[i]);

            FunctionDB.execute(sql, null);
            FunctionDB.execute(sql2, null);
        }
    }

     protected MeubleWorker mapRow(ResultSet result) 
        throws Exception 
    {
        String idMeubleWorker = result.getString("id_meuble_worker");
        String idMeuble = result.getString("id_meuble");
        String idWorker = result.getString("id_worker");
        String idVolume = result.getString("id_volume");
        int number = result.getInt("number_worker");
        double salary = result.getDouble("salary");

        Meuble me = new Meuble();
        Meuble meuble = me.getSpecificMeuble(idMeuble);
        Worker wo = new Worker();
        Worker worker = wo.getSpecificWorker(idWorker);
        Volume vo = new Volume();
        Volume volume = vo.getSpecificVolume(idVolume);

        return new MeubleWorker(idMeubleWorker, meuble, worker ,volume, number, salary);
    }


    public static double getPrixRevient(String idMeuble) {
        String sql = "SELECT prix_revient FROM v_prix_de_revient WHERE id_meuble ='"+idMeuble+"'";
        String sql2 = "SELECT total_material_price FROM v_price_meuble WHERE id_meuble ='"+idMeuble+"'";

        System.out.println("sql1" + sql);
        System.out.println("sql2"  + sql2);

        BigDecimal price = (BigDecimal) FunctionDB.fetch(sql, null).get(0);   
        BigDecimal price2 = (BigDecimal) FunctionDB.fetch(sql2, null).get(0);     

        System.out.println("1 : " + price.doubleValue() + " 2 : " + price2.doubleValue());
        return (price.doubleValue() + price2.doubleValue());
    }


    public static double getBenefice(double prixAchat, String idMeuble) {
        double revient = MeubleWorker.getPrixRevient(idMeuble);  
        
        System.out.println("Prix Achat : " + prixAchat + " - Prix revient : " + revient + " = " + (prixAchat - revient));
        return (prixAchat - revient);
    }


    public static Vector<Meuble> getBeneficeInRange(double min, double max) {

        try {
            Meuble meu = new Meuble();
            Meuble[] all = meu.getAll(Meuble.class, null);
            Vector<Double> benefs = new Vector<>();

            Vector<Meuble> res = new Vector<>();
    
    
            for (Meuble meuble : all) {
                double prixAchatMeuble = Meuble.getPrixAchat(meuble.getId());
                System.out.println("bouvl : " + prixAchatMeuble);
                double benefice = MeubleWorker.getBenefice(prixAchatMeuble, meuble.getId());

                meuble.setBenef(benefice);
                System.out.println("Benef of " + meuble.getIntitule() + " = " + benefice);

                if (benefice >= min && benefice <= max)
                { res.add(meuble); }
            } 

            return res;
        } 
        
        catch (Exception e) 
        { e.printStackTrace(); }
        
        return null;
    }

    public String getId() 
    { return id;}
    
    public Meuble getMeuble () 
    { return meuble; }

    public Worker getWorker() 
    { return worker; }

    public Volume getVolume() 
    { return volume; }

    public int getNumber() 
    { return number; }

    public double getSalary() 
    { return salary; }
    
    public void setId(String id)
        throws Exception 
    {
        if (id.length() == 0 || id == null)
        { throw new Exception ("Id can't be null !"); }
        
        this.id = id;
    }

    public void setMeuble(Meuble meuble) 
        throws Exception 
    {
        if (meuble == null) {
            throw new IllegalArgumentException("Meuble cannot be null");
        }
        this.meuble = meuble;
    }
    
    public void setWorker(Worker worker) 
        throws Exception 
    {
        if (worker == null) {
            throw new IllegalArgumentException("Worker cannot be null");
        }
        this.worker = worker;
    }
    
    public void setNumber(int number) 
        throws Exception 
    {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be greater than 0");
        }
        this.number = number;
    }
    
    public void setSalary(double salary) 
        throws Exception 
    {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }
    
    public void setVolume(Volume volume) 
        throws Exception 
    {
        if (volume == null) {
            throw new IllegalArgumentException("Volume cannot be null");
        }
        this.volume = volume;
    }


    public static void main(String[] args) {
        Vector<Meuble> res = MeubleWorker.getBeneficeInRange(100, 500);

        for (Meuble m : res)
        { System.out.println(m.getIntitule()); }
    }
}
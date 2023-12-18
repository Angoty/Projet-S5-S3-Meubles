/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.caracteristiques;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import models.connection.Connect;
import java.util.Vector;

/**
 *
 * @author P15-51-ANGOTY
 */
public class Materiel {
    String id;
    String intitule;

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception{
        if(id.length()>7 || id==null){
            throw new Exception("id materiel invalide");
        }
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) throws Exception{
        if(intitule==null || intitule.length()==0 || intitule.equals("")){
            throw new Exception("intitule du materiel nulle");
        }
        this.intitule = intitule;
    }

    public Materiel(String id, String intitule) throws Exception{
        this.id = id;
        this.intitule = intitule;
    }

    public Materiel() throws Exception{
    }
    
    
    public void insertMateriel(boolean check, String materiel, Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectPostgreSql();
                valid=false;
            } 
            stmt= con.createStatement();
            this.setIntitule(materiel);
            String sql="INSERT INTO Materiel VALUES(DEFAULT, '"+this.getIntitule()+"')";
            if(check==true){
                throw new Exception("Ce materiel existe deja");
            }
            System.out.println(sql);
            stmt.executeUpdate(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(!valid){ con.close(); }
            if(stmt!=null){ stmt.close(); }
        }
    }
    
    public Materiel[] getMateriels(Connection con)throws Exception{
        Vector<Materiel> listMateriel= new Vector<Materiel>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectPostgreSql();
                valid=false;
            }
            String sql = "SELECT * FROM Materiel ";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                Materiel m = new Materiel(id, intitule);
                listMateriel.add(m);
            }
        } catch (Exception e) {   
            e.printStackTrace(); 
        }finally{
            try {
                if( state!=null ){ state.close(); }
                if(result!=null ){ result.close(); }
                if(valid==false || con !=null){ con.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Materiel[] materiels= new Materiel[listMateriel.size()];
        listMateriel.toArray(materiels);
        return materiels;
    }

    public boolean check(String intitule, Connection con)throws Exception{
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        boolean misy=true;
        int count=0;
        try {
            if(con==null){
                con=Connect.connectPostgreSql();
                valid=false;
            }
            String sql = "SELECT count(*) FROM "+this.getClass().getSimpleName()+ " WHERE intitule='"+intitule+"'";
            state = con.createStatement();
            System.out.println(sql);
            result = state.executeQuery(sql);
            while(result.next()){
                count= result.getInt(1);
                System.out.println(id);
            }
            if(count==0){
                misy=false;   
            }
        } catch (Exception e) {  
            e.printStackTrace();
        }finally{
            try {
                if( state!=null ){ state.close(); }
                if(result!=null ){ result.close(); }
                if(valid==false || con !=null){ con.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return misy;
    }

    public static void main(String[] args) throws Exception{
        Materiel m = new Materiel();
        boolean jerena = m.check("boiss", null);
        System.out.println(jerena);
    }
}

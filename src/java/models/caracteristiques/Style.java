/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.caracteristiques;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import models.connection.Connect;

/**
 *
 * @author P15-51-ANGOTY
 */
public class Style {
    String id;
    String intitule;
    Materiel[] materiels;

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception{
        if(id.length()>7 || id==null){
            throw new Exception("id style invalide");
        }
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) throws Exception{
        if(intitule==null || intitule.length()==0 || intitule.equals("  ")){
            throw new Exception("intitule du style nulle");
        }
        this.intitule = intitule;
    }

    public Materiel[] getMateriels() {
        return materiels;
    }

    public void setMateriels(Materiel[] materiels) {
        this.materiels = materiels;
    }

    public Style() throws Exception{
    }

    public Style(String id, String intitule) throws Exception{
        this.id = id;
        this.intitule = intitule;
    }

    public Style(String id, String intitule, Materiel[] materiels) throws Exception{
        this.id = id;
        this.intitule = intitule;
        this.materiels = materiels;
    }

    
    

    public void insertStyle(boolean check, String intitule, Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectPostgreSql();
                valid=false;
            } 
            stmt= con.createStatement(); 
            this.setIntitule(intitule);           
            String sql="INSERT INTO Style VALUES(DEFAULT, '"+this.getIntitule()+"')";
            if(check==true){
                throw new Exception("Ce style existe deja");
            }
            stmt.executeUpdate(sql);
            System.out.println(sql);
        }catch(Exception e){
            throw e;
        }finally{
            if(!valid){ con.close(); }
            if(stmt!=null){ stmt.close(); }
        }
    }

    public Style getStyleRecent(Connection con)throws Exception{
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        Style style=new Style();
        try {
            if(con==null){
                con=Connect.connectPostgreSql();
                valid=false;
            }
            String sql = "SELECT * FROM Style ORDER BY id_style DESC LIMIT 1";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                style = new Style(id, intitule);
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
        return style;
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
            throw e;
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


    public void insertStyleMateriel(String[] materiels, String id, Connection con)throws Exception{
        boolean valid=true;
        Statement stmt =null;
        try{
            if(con==null){
                con = Connect.connectPostgreSql();
                valid=false;
            } 
            stmt= con.createStatement();            
            for(int i=0; i<materiels.length; i++){
                String sql="INSERT INTO StyleMateriel VALUES(DEFAULT, '"+materiels[i]+"', '"+id+ "')";
                stmt.executeUpdate(sql);
                System.out.println(sql);
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            if(!valid){ con.close(); }
            if(stmt!=null){ stmt.close(); }
        }
    }

    public Style getListeMateriel(String id, Connection con) throws Exception{
        Vector<Materiel> listMateriel= new Vector<Materiel>();
        Style style=null;
        String idStyle="";
        String intituleStyle="";
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectPostgreSql();
                valid=false;
            }
            String sql = "select * from v_style_materiel where id_style='"+id+"'";
            state = con.createStatement();
            System.out.println(sql);
            result = state.executeQuery(sql);
            while(result.next()){
                idStyle=result.getString(1);
                intituleStyle=result.getString(2);
                String idMateriel= result.getString(3);
                String intituleMateriel= result.getString(4);
                Materiel m = new Materiel(idMateriel, intituleMateriel);
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
        style  = new Style(idStyle, intituleStyle, materiels);
        return style;   
    }


    public Style[] getStyles(Connection con) throws Exception{
        Vector<Style> listStyle= new Vector<Style>();
        boolean valid=true;
        Statement state=null;
        ResultSet result=null;
        try {
            if(con==null){
                con=Connect.connectPostgreSql();
                valid=false;
            }
            String sql = "select * from style";
            state = con.createStatement();
            result = state.executeQuery(sql);
            while(result.next()){
                String id= result.getString(1);
                String intitule= result.getString(2);
                Style s = new Style(id, intitule);
                listStyle.add(s);
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
        Style[] style= new Style[listStyle.size()];
        listStyle.toArray(style);        
        return style;   
    }
}

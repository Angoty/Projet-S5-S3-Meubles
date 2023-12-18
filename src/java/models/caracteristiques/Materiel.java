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
    
    
    
}

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

    
    

    
}

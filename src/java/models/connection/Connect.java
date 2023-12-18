/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author P15-51-ANGOTY
 */
public class Connect {
    public static Connection connectPostgreSql() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meubles", "postgres", "mdpprom15");
        return con;
    }
}

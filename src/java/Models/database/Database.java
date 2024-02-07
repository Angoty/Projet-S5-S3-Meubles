package Models.database;

import java.sql.Connection;
import java.sql.*;

public class Database {
    public static Connection connectionPostgres() 
        throws Exception 
    {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meuble_final", "postgres", "mdpprom15");

        return con;
    }
}
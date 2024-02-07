package Models.database;

import java.sql.*;
import java.util.*;

import Models.components.Poste;

import java.lang.reflect.Array;

public abstract class BaseModel<T> {
    
    public T[] getAll(Class<T> clazz, Connection con)
        throws Exception
    {
        Vector<T> list= new Vector<T>();

        boolean valid = true;
        Statement state = null;
        ResultSet result = null;

        try {
            if(con == null) {
                con = Database.connectionPostgres();
                valid = false;
            }

            String sql = "SELECT * FROM " + this.getClass().getSimpleName();
            System.out.println(sql);
            state = con.createStatement();
            result = state.executeQuery(sql);

            while(result.next()) {
                T item = mapRow(result);
                list.add(item);
            }
        } 
        
        catch (Exception e) 
        { e.printStackTrace(); }
        
        finally {
            try {
                if (state != null) state.close(); 
                if (result != null ) result.close(); 
                if (valid == false || con !=null) con.close(); 
            } 
            
            catch (Exception e) 
            { e.printStackTrace(); }
        }

        T[] items = (T[]) Array.newInstance(clazz, list.size());
        list.toArray(items);

        return items;
    }

    protected abstract T mapRow(ResultSet result) throws Exception;



}
package Models.database;

import java.sql.*;
import java.sql.Connection;
import java.util.Vector;
import java.lang.reflect.Array;

public class FunctionDB {
    // EXECUTE A QUERY
    public static void execute(String query, Connection con) {
        Statement statement = null;
        try {
            if (con == null) 
            { con = Database.connectionPostgres(); }
            
            statement = con.createStatement();
            statement.executeQuery(query);
        }

        catch (Exception e) 
        { e.printStackTrace(); }

        finally {
            try {
                if (con != null)  con.close();
                if (statement != null) statement.close(); 
            } 
            catch (Exception ex) 
            { ex.printStackTrace(); }
        }
    } 

    // FETCH DATA
    public static Vector fetch(String query, Connection con) {
        Vector result = new Vector<>();
        Statement statement = null;
        ResultSet set = null;

        try {
            if (con == null) 
            { con = Database.connectionPostgres(); }
            
            statement = con.createStatement();
            set = statement.executeQuery(query);

            ResultSetMetaData data = set.getMetaData();
            int countColumn = data.getColumnCount();

            while(set.next()) {
                for (int i = 1; i <= countColumn; i++)
                { result.add(set.getObject(i)); }
            }
            
            
            return result;
        } 
        
        catch (Exception e) 
        { e.printStackTrace(); }

        finally {
            try {
                if (con != null)  con.close();
                if (statement != null) statement.close();
                if (set != null) set.close();
            } 
            catch (Exception ex) 
            { ex.printStackTrace(); }
        }

        return result;
    }

    // CLASS CONVERTOR 
    public static <T> Vector<T> convert(Vector<?> inputVector, T targetType) {
        Vector<T> resultVector = new Vector<>();

        for (Object obj : inputVector) {
            if (obj.getClass().equals(targetType.getClass())) 
            { resultVector.add((T) obj); }
        }
        
        return resultVector;
    }
}
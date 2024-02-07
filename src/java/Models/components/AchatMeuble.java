package Models.components;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Models.database.BaseModel;
import Models.database.Database;
import Models.database.FunctionDB;

public class AchatMeuble extends BaseModel<AchatMeuble>{
    Client client;
    Meuble meuble;
    int quantite;
    Date dateAchat;

    public AchatMeuble()
    { }

    public AchatMeuble(Client client, Meuble meuble, int quantite, Date dateAchat) 
        throws Exception
    {
        this.setClient(client);
        this.setMeuble(meuble);
        this.setQuantite(quantite);
        this.setDateAchat(dateAchat);
    }
   
    public static Map<String, Integer> getAchatsParGenre() 
        throws Exception
    {
        Map<String, Integer> achatsParGenreMap = new HashMap<>();

        try (Connection connection = Database.connectionPostgres()) {

            String sql = "SELECT * FROM v_achat_par_genre";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String genre = resultSet.getString("Genre");
                        int nombreAchats = resultSet.getInt("NombreAchats");

                        achatsParGenreMap.put(genre, nombreAchats);
                    }
                }
            }
        } 
        
        catch (SQLException e) 
        { e.printStackTrace(); }

        return achatsParGenreMap;
    }

    public static Map<String, Integer> getAchatsParStyle() 
        throws Exception
    {
        Map<String, Integer> achatsParStyle = new HashMap<>();

        try (Connection connection = Database.connectionPostgres()) {

            String sql = "SELECT * FROM v_achat_par_style";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String style = resultSet.getString("style_intitule");
                        int nombreAchats = resultSet.getInt("nombre_achats");

                        achatsParStyle.put(style, nombreAchats);
                    }
                }
            }
        } 
        
        catch (SQLException e) 
        { e.printStackTrace(); }

        return achatsParStyle;
    }


    // ABSTRACT METHOD MAP ROW 
    @Override
    protected AchatMeuble mapRow(ResultSet result) 
        throws Exception 
    {
        String id = result.getString(1);
        String idMeub = result.getString(2);
        int qte = result.getInt(3);
        Date dt = result.getDate(4);
   
        Client c = new Client().getSpecificClient(id);
        Meuble m = new Meuble().getSpecificMeuble(idMeub);

        return new AchatMeuble(c, m, qte, dt);
    }

    public Client getClient() 
    { return client; }

    public void setClient(Client client) 
        throws Exception
    {
        if (client == null) 
        { throw new Exception("Client can't be null ! : " + client.getIntitule()); }

        this.client = client;
    }

    public Meuble getMeuble() 
    { return meuble; }

    public void setMeuble(Meuble meuble) 
        throws Exception
    {
        if (meuble == null) 
        { throw new Exception("Meuble can't be null !"); }

        this.meuble = meuble;
    }

    public int getQuantite() 
    { return quantite; }

    public void setQuantite(int quantite) 
        throws Exception
    {
        if (quantite <= 0) 
        { throw new Exception("Invalid value !"); }

        this.quantite = quantite;
    }

    public Date getDateAchat() 
    { return dateAchat; }

    public void setDateAchat(Date dateAchat) 
        throws Exception
    {
        if (dateAchat == null)
        { throw new Exception("Date can't be null"); }

        this.dateAchat = dateAchat;
    }   
}
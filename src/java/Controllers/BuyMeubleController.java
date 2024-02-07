package Controllers;

import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;

public class BuyMeubleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Meuble m = new Meuble();
            Volume v = new Volume();

            Meuble[] meubles = m.getAll(Meuble.class, null);
            Volume[] volumes = v.getAll(Volume.class, null);
            Client[] clients = new Client().getAll(Client.class, null);

            request.setAttribute("all_volumes", volumes);
            request.setAttribute("all_meubles", meubles);
            request.setAttribute("all_clients", clients);
            System.out.println(clients.length);

            RequestDispatcher dispatch = request.getRequestDispatcher("buy-meuble.jsp");
            dispatch.forward(request, response);
        } 

        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("buy-meuble.jsp");
            dispatch.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            String idClient = request.getParameter("selected-client");
            String idMeuble = request.getParameter("selected-meuble");
            String idVolume = request.getParameter("selected-volume");
            int qte = Integer.valueOf(request.getParameter("qte"));
            String dt = request.getParameter("date");

            new MeubleQuantityMateriel().insertAchatMeuble(idClient, idMeuble, qte, dt);
            new MeubleQuantityMateriel().constructMeuble(idMeuble, qte, dt);
            double priceTotal = MeubleQuantityMateriel.getPriceMeuble(idMeuble);

            Client c = new Client().getSpecificClient(idClient);
            c.transaction(priceTotal);

            Meuble m = new Meuble();
            Volume v = new Volume();

            Meuble[] meubles = m.getAll(Meuble.class, null);
            Volume[] volumes = v.getAll(Volume.class, null);
            Client[] clients = new Client().getAll(Client.class, null);

            request.setAttribute("all_volumes", volumes);
            request.setAttribute("all_meubles", meubles);
            request.setAttribute("all_clients", clients);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("buy-meuble.jsp");
            dispatch.forward(request, response);
            
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("buy-meuble.jsp");
            dispatch.forward(request, response);
        }  
    }
}
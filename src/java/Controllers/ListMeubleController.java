package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.*;

public class ListMeubleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            String idMateriel = (String) request.getParameter("selected-materiel");

            MeubleQuantityMateriel meuble = new MeubleQuantityMateriel();
            MeubleQuantityMateriel[] all = meuble.getByIdMateriel(idMateriel);
            Materiel mat = new Materiel();
            Materiel[] materiels = mat.getAll(Materiel.class, null);

            request.setAttribute("all-materiels", materiels);

            request.setAttribute("all-meuble-qte", all);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("find-meuble.jsp");
            dispatch.forward(request, response);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {

    }
}
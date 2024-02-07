package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.Materiel;
import Models.components.Meuble;

public class GetListMaterielController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            String idMeuble = request.getParameter("selected-meuble");
            String idVolume = request.getParameter("selected-volume");
            
            Meuble meuble = new Meuble();
            Meuble specMeuble = meuble.getSpecificMeuble(idMeuble);

            Materiel[] materiels = specMeuble.getAllMateriel();

            request.setAttribute("all-materiels", materiels);
            request.setAttribute("id-meuble", idMeuble);
            request.setAttribute("id-volume", idVolume);
    
            RequestDispatcher dispatch = request.getRequestDispatcher("materiel-qte.jsp");
            dispatch.forward(request, response);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-materiel-qte.jsp");
            dispatch.forward(request, response);
        }  
    }
}
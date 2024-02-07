package Controllers;

import java.io.*;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


import Models.components.*;

public class InsertMaterielController extends HttpServlet {
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
            String intitule = request.getParameter("intitule");
            String strPrice = request.getParameter("price");

            double price = Double.valueOf(strPrice);
           
            Materiel materiel = new Materiel();
            materiel.setIntitule(intitule);
            materiel.setUnitPrice(price);

            materiel.insertMateriel();
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-materiel.jsp");
            dispatch.forward(request, response);
        } 
        
        catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-materiel.jsp");
            dispatch.forward(request, response);
        }  
    }
}
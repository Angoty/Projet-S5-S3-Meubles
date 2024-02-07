package Controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertMeubleController extends HttpServlet {

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
            String idStyle = request.getParameter("selected-style");
            String idVolume = request.getParameter("selected-volume");

            Meuble meuble = new Meuble();
            Style s = new Style();
            Volume v = new Volume();

            meuble.setIntitule(intitule);
            meuble.setStyle(s.getSpecificStyle(idStyle));
            meuble.setVolume(v.getSpecificVolume(idVolume));

            meuble.insertMeuble();
            response.sendRedirect("index.jsp");
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }  
    }
}
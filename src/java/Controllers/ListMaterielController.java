package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.Materiel;

public class ListMaterielController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Materiel m = new Materiel();
            Materiel[] materiels = m.getAll(Materiel.class, null);

            request.setAttribute("all_materiels", materiels);
            RequestDispatcher dispatch = request.getRequestDispatcher("list-materiel.jsp");
        
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
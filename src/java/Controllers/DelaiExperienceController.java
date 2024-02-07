package Controllers;

import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;
import Models.database.Database;
import Models.database.FunctionDB;

public class DelaiExperienceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Poste[] allPoste = new Poste().getAll(Poste.class, null);
            request.setAttribute("all_postes", allPoste);
    
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-experience.jsp");
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
        try {
            String idPoste = request.getParameter("selected-poste");
            int delai = Integer.valueOf(request.getParameter("delai"));

            String sql = " INSERT INTO ExperiencePoste (id_poste, delai) VALUES ('"+idPoste+"', "+delai+")";

            FunctionDB.execute(sql, null);
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
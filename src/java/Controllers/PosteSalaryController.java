package Controllers;

import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;
import Models.database.Database;
import Models.database.FunctionDB;

public class PosteSalaryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Poste[] allPoste = new Poste().getAll(Poste.class, null);
            request.setAttribute("all_postes", allPoste);
    
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-poste-salary.jsp");
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
            int multi = Integer.valueOf(request.getParameter("mulitiplicateur"));

            String sql = " INSERT INTO SalaryPoste VALUES ('"+idPoste+"', "+multi+")";

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
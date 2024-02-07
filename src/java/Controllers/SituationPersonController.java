package Controllers;

import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;
import Models.database.FunctionDB;

public class SituationPersonController extends HttpServlet {
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
            Date date = Date.valueOf(request.getParameter("date"));
            Person[] all = new Person().getAll(Person.class, null);

            // UPDATE EACH TURN
            String updateSql = " DELETE FROM MvtPoste ";
            FunctionDB.execute(updateSql, null);
            
            for (Person person : all) {
                new Person().updatePoste(person, date);
                System.out.println("person ID : " + person.getId());
            }

            MvtPoste[] allMvt = new MvtPoste().getAll(MvtPoste.class, null);
            request.setAttribute("all_mvt", allMvt);

            RequestDispatcher dispatch = request.getRequestDispatcher("situation-person.jsp");
            dispatch.forward(request, response);
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }  
    }
}
package Controllers;

import java.io.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertPersonController extends HttpServlet {
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
            String intitule = request.getParameter("name");
            Date date = Date.valueOf(request.getParameter("date"));
            double salary = Double.valueOf(request.getParameter("salary"));

            Poste p = new Poste();
            Poste poste = p.getSpecificPoste("POS0001");

            Person person = new Person();

            person.setPoste(poste);
            person.setIntitule(intitule);
            person.setDateEmbauche(date);
            person.setSalary(salary);

            person.insertPerson();
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
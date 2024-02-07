package Controllers;

import java.io.*;
import java.sql.Date;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertClientController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        Vector<String> allGender = Client.getAllGender();

        request.setAttribute("all_gender", allGender);
        RequestDispatcher dispatch = request.getRequestDispatcher("insert-client.jsp");
        dispatch.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            double budget = Double.valueOf(request.getParameter("budget"));

            Client client = new Client();
            client.setIntitule(name);
            client.setGender(gender);
            client.setBudget(budget);

            client.insertClient();
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
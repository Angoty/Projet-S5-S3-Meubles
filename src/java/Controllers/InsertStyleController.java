package Controllers;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;
import Models.database.FunctionDB;

public class InsertStyleController extends HttpServlet {
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
            String nameStyle = request.getParameter("name-style"); 
            String[] seletedStyle = request.getParameterValues("selected-materiels");

            Style style = new Style();
            style.setIntitule(nameStyle);
            style.insertStyle();

            for (int i = 0; i < seletedStyle.length; i++) {
                String query = "insert into StyleMateriel values ('"+style.getId(nameStyle)+"', '"+seletedStyle[i]+"')";
                FunctionDB.execute(query, null);
            }

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
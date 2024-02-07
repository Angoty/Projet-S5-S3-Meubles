package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import Models.components.*;

public class GetStyleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Style m = new Style();
            Volume v = new Volume();
            Style[] styles = m.getAll(Style.class, null);
            Volume[] volumes = v.getAll(Volume.class, null);

            request.setAttribute("all_styles", styles);
            request.setAttribute("all_volumes", volumes);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-meuble.jsp");
        
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
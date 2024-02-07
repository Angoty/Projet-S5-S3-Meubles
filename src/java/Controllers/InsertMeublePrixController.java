package Controllers;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertMeublePrixController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Meuble m = new Meuble();
            Meuble[] meubles = m.getAll(Meuble.class, null);

            Volume v = new Volume();
            Volume[] vol = v.getAll(Volume.class, null);

            request.setAttribute("all_meubles", meubles);
            request.setAttribute("all_volumes", vol);

            RequestDispatcher dispatch = request.getRequestDispatcher("insert-meuble-prix-achat.jsp");
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
            String id = request.getParameter("selected-meuble");
            String vol = request.getParameter("selected-volume");
            double prix = Double.valueOf(request.getParameter("prix"));
    
    
            Meuble.insertMeublePrixAchat(id, vol, prix); 
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
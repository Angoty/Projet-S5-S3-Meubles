package Controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertWorkerController extends HttpServlet {
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
            String intitule = request.getParameter("type");

            Worker worker = new Worker();
            worker.setIntitule(intitule);

            worker.insertWorker();
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
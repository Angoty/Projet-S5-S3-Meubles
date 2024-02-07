package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.*;

public class GetMeubleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Meuble m = new Meuble();
            Volume v = new Volume();

            Meuble[] meubles = m.getAll(Meuble.class, null);
            Volume[] volumes = v.getAll(Volume.class, null);

            request.setAttribute("all_volumes", volumes);
            request.setAttribute("all_meubles", meubles);

            RequestDispatcher dispatch = request.getRequestDispatcher("insert-materiel-qte.jsp");
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
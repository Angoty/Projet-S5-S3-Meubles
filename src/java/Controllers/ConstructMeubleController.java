package Controllers;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;

import Models.components.Meuble;
import Models.components.MeubleQuantityMateriel;
import Models.components.Volume;

public class ConstructMeubleController extends HttpServlet {

    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Meuble m = new Meuble();
            Meuble[] meubles = m.getAll(Meuble.class, null);

            request.setAttribute("all_meubles", meubles);

            RequestDispatcher dispatch = request.getRequestDispatcher("construct-meuble.jsp");
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
            String idMeuble = request.getParameter("selected-meuble");
            int qte = Integer.valueOf(request.getParameter("qte"));
            String date = request.getParameter("date-stock");

            MeubleQuantityMateriel.constructMeuble(idMeuble, qte, date);
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
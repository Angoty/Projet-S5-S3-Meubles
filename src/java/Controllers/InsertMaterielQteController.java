package Controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertMaterielQteController extends HttpServlet {

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
            String idMeuble = request.getParameter("id-meuble");
            String idVolume = request.getParameter("id-volume");

            String[] strQte = request.getParameterValues("qte-materiels");
            String[] id = request.getParameterValues("id-materiels"); 
            int[] qte = new int[strQte.length];

            for (int i = 0; i < qte.length; i++)
            { qte[i] = Integer.valueOf(strQte[i]); }

            MeubleQuantityMateriel.insertMeubleQteMateriel(idMeuble, idVolume, id, qte);  

            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
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
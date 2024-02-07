package Controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Models.components.Materiel;
import Models.components.Stock;


public class InsertStockController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Materiel m = new Materiel();
            Materiel[] materiels = m.getAll(Materiel.class, null);

            request.setAttribute("all_materiels", materiels);
            RequestDispatcher dispatch = request.getRequestDispatcher("insert-stock.jsp");
        
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
            String idMateriel = request.getParameter("selected-materiel");
            int qte = Integer.valueOf(request.getParameter("qte"));
            String date = request.getParameter("date-stock");


            System.out.println(idMateriel);

            Materiel m = new Materiel();
            Materiel mat = m.getSpecificMateriel(idMateriel);

            Stock stock = new Stock();
            stock.setMateriel(mat);
            stock.setQuantity(qte);

            stock.insertStock(date);
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
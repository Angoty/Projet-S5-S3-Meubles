package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.Materiel;
import Models.components.Stock;

public class GetStockController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Stock m = new Stock();
            Stock[] stocks = m.getAllStock(null);

            Materiel mat = new Materiel();
            Materiel[] materiels = mat.getAll(Materiel.class, null);

            request.setAttribute("all_materiels", materiels);
            request.setAttribute("all_stocks", stocks);

            RequestDispatcher dispatch = request.getRequestDispatcher("list-stock.jsp");
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
            String idMateriel = (String) request.getParameter("selected-materiel");
            Stock st = new Stock();
            Stock[] all = st.getAllStock(null);


            for (Stock sto : all) {
                if (sto.getMateriel().getId().equals(idMateriel)) {
                    request.setAttribute("filter-stock", sto);
                    RequestDispatcher dispatch = request.getRequestDispatcher("filter-stock.jsp");
                    dispatch.forward(request, response);
                }
            }
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            
            RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }  
    }
}
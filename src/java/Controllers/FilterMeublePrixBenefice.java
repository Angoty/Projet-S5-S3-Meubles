package Controllers;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.*;
import java.util.*;

public class FilterMeublePrixBenefice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            double price1 = Double.valueOf(request.getParameter("price-1"));
            double price2 = Double.valueOf(request.getParameter("price-2"));

            Vector<Meuble> res = MeubleWorker.getBeneficeInRange(price1, price2);
            request.setAttribute("meuble-benef", res);

            RequestDispatcher dispatch = request.getRequestDispatcher("list-meuble-benef.jsp");
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
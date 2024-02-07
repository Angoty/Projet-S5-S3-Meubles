package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.components.*;

public class TableauStatController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            AchatMeuble[] allAchat = new AchatMeuble().getAll(AchatMeuble.class, null);
            Map<String, Integer> tab = AchatMeuble.getAchatsParGenre();
            Map<String, Integer> tab2 = AchatMeuble.getAchatsParStyle();
            request.setAttribute("all-achat", allAchat);
            request.setAttribute("all-achat-genre", tab);
            request.setAttribute("all-achat-style", tab2);

            RequestDispatcher dispatch = request.getRequestDispatcher("tableau-achat.jsp");
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
            AchatMeuble[] allAchat = new AchatMeuble().getAll(AchatMeuble.class, null);

            String style = request.getParameter("selected-style");
            String gender = request.getParameter("selected-gender");

            Map<String, Integer> tab = AchatMeuble.getAchatsParGenre();
            Map<String, Integer> tab2 = AchatMeuble.getAchatsParStyle();

            Integer valueForGenre = tab.get(gender);
            Integer valueForStyle = tab2.get(style);

            Map<String, Integer> ans1 = new HashMap<String, Integer>();
            Map<String, Integer> ans2 = new HashMap<String, Integer>();

            ans1.put(style, valueForStyle);
            ans2.put(gender, valueForGenre);

            if (valueForGenre != null && valueForStyle != null) {
                request.setAttribute("style", ans1);
                request.setAttribute("genre", ans2);
                request.setAttribute("all-achat", allAchat);
                request.setAttribute("all-genre", tab);

            
                RequestDispatcher dispatch = request.getRequestDispatcher("filtered-statistique.jsp");
                dispatch.forward(request, response);
            } 
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
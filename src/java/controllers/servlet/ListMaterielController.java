/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.caracteristiques.Materiel;
import models.caracteristiques.Style;

/**
 *
 * @author P15-51-ANGOTY
 */
public class ListMaterielController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            RequestDispatcher dispat = request.getRequestDispatcher("views/list_style.jsp");
            dispat.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String id=request.getParameter("idStyle");
        try{
            RequestDispatcher dispat = request.getRequestDispatcher("views/list_materiel.jsp");
            dispat.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
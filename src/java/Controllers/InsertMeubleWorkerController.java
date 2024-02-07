package Controllers;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.*;

import Models.components.*;

public class InsertMeubleWorkerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        try {
            Meuble m = new Meuble();
            Meuble[] meubles = m.getAll(Meuble.class, null);

            request.setAttribute("all_meubles", meubles);

            Worker w = new Worker();
            Worker[] allWorker = w.getAll(Worker.class, null);

            request.setAttribute("all_worker", allWorker);

            RequestDispatcher dispatch = request.getRequestDispatcher("insert-meuble-worker.jsp");
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
    
            String[] idWorkers = request.getParameterValues("selected-workers");
            String[] numbers = request.getParameterValues("number-worker");
            String[] salary = request.getParameterValues("salary-worker");


            int[] listNum = new int[numbers.length];
            double[] listSal = new double[salary.length];

            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] != "" && salary[i] != "") {
                    listNum[i] = Integer.valueOf(numbers[i]);            
                    listSal[i] = Double.valueOf(salary[i]);
                }
            }
            
            MeubleWorker meu = new MeubleWorker();
            meu.insertMeubleWorker(idMeuble, idWorkers, listSal, listNum);

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
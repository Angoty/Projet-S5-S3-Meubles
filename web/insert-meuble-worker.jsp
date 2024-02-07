<%@page import="Models.components.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/form-style.css">
    <title>Document</title>
</head>
<body>
    <div class="wrapper">
        <%@ include file='side-bar.jsp'%>

        <!-- Page Content  -->
        <div id="content">

            <%@ include file='nav.jsp'%>

            <div class="form">
                <h3 style="text-align: center; padding-top: 50px;">Personnels</h3>
                <div style="margin-top: -150px;">
                    <form action="./InsertMeubleWorkerController" method="post">
                        <div class="form-group">
                            <label for="meuble">Meuble:
                                <select class="form-control" name="selected-meuble">
                                    <% if (request.getAttribute("all_meubles") != null) {
                                        Meuble[] meubles = (Meuble[]) request.getAttribute("all_meubles");
                                        for (int i = 0; i < meubles.length; i++) { %>
                                            <option value="<%= meubles[i].getId() %>"><%= meubles[i].getIntitule() %></option>
                                        <% } %>
                                    <% } %>
                                </select>
                            </label>
                            
                        </div>
                        <label style="">Materiaux nécéssaires:
    
                        <%  if ( request.getAttribute("all_worker") != null) {
                            Worker[] workers = (Worker[]) request.getAttribute("all_worker");
                            for (int i = 0; i < workers.length; i++) { %>                            
                                <div>
                                    <input class="form-check-input" style="margin-left:-740px; margin-top:5px;" type="checkbox"  name="selected-workers" value="<%= workers[i].getId() %>">
                                    <label class="form-check-label" for="interest1" style=" font-weight:200px; "> <%= workers[i].getIntitule() %> </label>
                    
                                </div>
                                <div>
                                    <input type="number" style="width: 100px; margin-left: 150px;margin-top: -150px;" name="salary-worker" placeholder="Salaire">
                                    <input type="number" style="width: 60px" name="number-worker" placeholder="Nb">
                                </div>
                            <% } %>
                        <%  }  %>
                        </label>
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </form>   
                </div>
                
            </div>
                     
        </div>
    </div>
</body>
</html>




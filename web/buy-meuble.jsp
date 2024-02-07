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
                <h3 style="text-align: center; padding-top: 50px;">Achat d'un meuble</h3>
                <form action="./BuyMeubleController" method="post">

                    <div class="form-group">
                        <label for="meuble" style="float: left;">Choose a Client:
                            <select class="form-control" name="selected-client">
                                <% if (request.getAttribute("all_clients") != null) {
                                    Client[] clients = (Client[]) request.getAttribute("all_clients");
                                    for (int i = 0; i < clients.length; i++) { %>
                                        <option value="<%= clients[i].getId() %>"><%= clients[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                        
                    </div>


                    <div class="form-group">
                        <label for="meuble" style="float: left;">Choose a Meuble:
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

                    <div class="form-group">
                        <label for="meuble" style="float: left;margin-top: 50px;">Choose a Volume:
                            <select class="form-control" name="selected-volume">
                                <% if (request.getAttribute("all_volumes") != null) {
                                    Volume[] volumes = (Volume[]) request.getAttribute("all_volumes");
                                    for (int i = 0; i < volumes.length; i++) { %>
                                        <option value="<%= volumes[i].getId() %>"><%= volumes[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                        
                    </div>

                    <div class="form-group">
                        <label for="date" style="float: left; margin-top: 50px;">Date:
                            <input type="date" style="width: 150px;" class="form-control" name="date" placeholder="Enter a date">
                        </label>
                        
                    </div>

                    <div class="form-group">
                        <label for="quantity"style="margin-top: 20px;">Quantité:
                            <input type="number"  class="form-control" name="qte" placeholder="Enter a quantity">
                        </label>
                        
                    </div>


                    <button type="submit" class="btn btn-primary">Valider</button>
                    
                </form>  
            </div>
            <% if (request.getAttribute("error") != null) { %>
                <% String error = (String) request.getAttribute("error"); %>
                <div class="alert alert-danger" style="width:300px ; margin-left: 300px;">
                    <%= error %>
                </div>
            <% } %> 

                     
        </div>
    </div>
</body>
</html>
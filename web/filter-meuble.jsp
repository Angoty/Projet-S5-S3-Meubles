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
            
                <h2 class="mb-4">Liste des meubles entre: </h2>

                <% if (request.getAttribute("error") != null) { %>
                    <% String error = (String) request.getAttribute("error"); %>
                    <div class="alert alert-danger">
                        <%= error %>
                    </div>
                <% } %>

                <form action="./FilterMeubleController" method="post">
                    <div class="form-group">
                        <label for="Date">
                            <input type="number" style="width: 200px; margin-left: -30px; " class="form-control" name="price-1" placeholder="Entez un prix">
                            <input type="number" style="width: 200px; margin-left: 180px; margin-top: -55px; float: left;" class="form-control" name="price-2" placeholder="Entez un prix">
                        </label>
                    </div>

                    <button type="submit" style="margin-top: -130px; margin-left: 470px;" class="btn btn-primary">Valider</button>
                </form> 
                <% 
                    if (request.getAttribute("all-filter-meuble") != null) {
                        MeubleQuantityMateriel[] materiels = (MeubleQuantityMateriel[]) request.getAttribute("all-filter-meuble");
                %>
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Meuble</th>
                                <th>Volume</th>
                                <th>Materiel</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < materiels.length; i++) { %>
                                <tr>
                                    <td><%= materiels[i].getIntituleById(materiels[i].getIdMeuble(), "meuble") %></td>
                                    <td><%= materiels[i].getIntituleById(materiels[i].getIdVolume(), "volume") %></td>
                                    <td><%= materiels[i].getIntituleById(materiels[i].getIdMateriel(), "materiel") %></td>
                                    <td><%= materiels[i].getQte() %></td>
                                    <td><%= materiels[i].getPriceMeuble() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>  
                <% } %>       
        </div>
    </div>
</body>
</html>
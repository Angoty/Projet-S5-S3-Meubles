<%@page import="Models.components.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/form-style.css">
    <link rel="stylesheet" href="./assets/table-style.css">
    <title>Document</title>
</head>
<body>
    <div class="wrapper">
        <%@ include file='side-bar.jsp'%>

        <!-- Page Content  -->
        <div id="content">

            <%@ include file='nav.jsp'%>
            <h2 class="mb-4">Liste des meubles faits en:</h2>

                <% if (request.getAttribute("error") != null) { %>
                    <% String error = (String) request.getAttribute("error"); %>
                    <div class="alert alert-danger">
                        <%= error %>
                    </div>
                <% } %>

                <form action="./ListMeubleController">
                    <div class="form-group">
                        <label for="meuble">Materiel:
                            <select class="form-control" name="selected-materiel" style="width: 150px">
                                <% if (request.getAttribute("all-materiels") != null) {
                                    Materiel[] materials = (Materiel[]) request.getAttribute("all-materiels");
                                    for (int i = 0; i < materials.length; i++) { %>
                                        <option value="<%= materials[i].getId() %>"><%= materials[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                        
                    </div>
                    <button type="submit" style="margin-top: -110px; margin-left: 200px;" class="btn btn-primary" >Voir</button>
                </form>    
                <% 
                if (request.getAttribute("all-meuble-qte") != null) {
                    MeubleQuantityMateriel[] materiels = (MeubleQuantityMateriel[]) request.getAttribute("all-meuble-qte");
                %>
    
                    <table class="table" style="width: 140%; margin-top: 100px;">
                        <thead>
                            <tr>
                                <th>Meuble</th>
                                <th>Volume</th>
                                <th>Materiel</th>
                                <th>Quantite</th>
                                <th>Prix</th>
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
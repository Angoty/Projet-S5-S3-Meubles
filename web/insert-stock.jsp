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
                <h3 style="text-align: center; padding-top: 50px;">Ajout de stock de materiel</h3>
                <form action="./InsertStockController" method="post">
                    <div class="form-group">
                        <label for="quantity" >Materiel:
                            <select class="form-control" name="selected-materiel" >
                                <% if (request.getAttribute("all_materiels") != null) {
                                    Materiel[] materiels = (Materiel[]) request.getAttribute("all_materiels");
                                    for (int i = 0; i < materiels.length; i++) { %>
                                        <option value="<%= materiels[i].getId() %>"><%= materiels[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                    </div>                
                    <div class="form-group" >
                        <label for="quantity" style="float: left;">Date:
                            <input type="date" class="form-control" name="date-stock">
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="quantity" style="float: left;">Quantity:
                            <input type="number" class="form-control" name="qte" placeholder="Entez le quantite de materiel">
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>        
            </div>
        </div>
    </div>

   
</body>
</html>
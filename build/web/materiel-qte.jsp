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
                <h3 style="text-align: center; padding-top: 50px;">Quantite de materiel:</h3>

                <% if (request.getAttribute("error") != null) { %>
                    <% String error = (String) request.getAttribute("error"); %>
                    <div class="alert alert-danger">
                        <%= error %>
                    </div>
                <% } %>
                <form action="./InsertMaterielQteController" method="post">
                    <% if (request.getAttribute("id-meuble") != null && request.getAttribute("id-volume") != null) { %>
                        <input type="text" name="id-meuble" value="<%= request.getAttribute("id-meuble") %>" hidden>
                        <input type="text" name="id-volume" value="<%= request.getAttribute("id-volume") %>" hidden>
                    <% } %>
                    <% if (request.getAttribute("all-materiels") != null) { %>
                        <% Materiel[] materiels = (Materiel[]) request.getAttribute("all-materiels"); %>
                        <% for (int i = 0; i < materiels.length; i++) { %>
                                    <input type="text" name="id-materiels" value="<%= materiels[i].getId() %>" hidden>
                                    <div class="form-group">
                                        <label for="" style="float: left;">
                                            <%= materiels[i].getIntitule()%><input type="text" class="form-control" name="qte-materiels" placeholder="Entrz la quantite">
                                        </label>
                                    </div>
                                <% } %>
                            <% } %>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>   
            </div>
                     
        </div>
    </div>
</body>
</html>
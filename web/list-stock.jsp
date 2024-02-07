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


            <h2 class="mb-4">List Meuble</h2>
            <% 
                if (request.getAttribute("all_stocks") != null && request.getAttribute("all_materiels") != null ) {
                    Stock[] stocks = (Stock[]) request.getAttribute("all_stocks");
            %>

                <form action="./GetStockController" method="post" class="form-container">

                    <div class="form-group">
                        <label for="meuble">Choose a Materiel:</label>
                        <select class="form-control" name="selected-materiel" style="width: 50%;">
                            <% if (request.getAttribute("all_materiels") != null) {
                                Materiel[] materiels = (Materiel[]) request.getAttribute("all_materiels");
                                for (int i = 0; i < materiels.length; i++) { %>
                                    <option value="<%= materiels[i].getId() %>"><%= materiels[i].getIntitule() %></option>
                                <% } %>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>


                <table class="table" style="width: 40%;">
                    <thead>
                        <tr>
                            <th>Intitule</th>
                            <th>Quantity</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i = 0; i < stocks.length; i++) { %>
                            <tr>
                                <td><%= stocks[i].getMateriel().getIntitule() %></td>
                                <td><%= stocks[i].getQuantity() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>

            <% } %>
        </div>
    </div>
</body>
</html>
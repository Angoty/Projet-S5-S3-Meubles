<%@page import="Models.components.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/table-style.css">
    <link rel="stylesheet" href="./assets/form-style.css">
    <title>Document</title>
</head>
<body>
    <div class="wrapper">
        <%@ include file='side-bar.jsp'%>

        <!-- Page Content  -->
        <div id="content">

            <%@ include file='nav.jsp'%>


            <center>
                <h2 class="mb-4">Construct a Meuble</h2>

                <% if (request.getAttribute("error") != null) { %>
                    <% String error = (String) request.getAttribute("error"); %>
                    <div class="alert alert-danger">
                        <%= error %>
                    </div>
                <% } %>

                        
                <% 
                    if (request.getAttribute("filter-stock") != null ) {
                        Stock stocks = (Stock) request.getAttribute("filter-stock");
                %>


                    <table class="table">
                        <thead>
                            <tr>
                                <th>Intitule</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                                <tr>
                                    <td><%= stocks.getMateriel().getIntitule() %></td>
                                    <td><%= stocks.getQuantity() %></td>
                                </tr>
                        </tbody>
                    </table>
                <% } %>

                <a href="./index.jsp"> BACK </a>
            </center>
        </div>
    </div>
</body>
</html>
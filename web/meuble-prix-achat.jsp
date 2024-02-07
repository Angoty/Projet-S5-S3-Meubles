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
                <h2 class="mb-4">Benefice entre:</h2>

                <% if (request.getAttribute("error") != null) { %>
                    <% String error = (String) request.getAttribute("error"); %>
                    <div class="alert alert-danger">
                        <%= error %>
                    </div>
                <% } %>

                <form action="./FilterMeublePrixBenefice" method="get">
                    <div class="form-group">
                        <label for="">
                            <input type="number" style="width: 200px; margin-left: -30px; " class="form-control" name="price-1" placeholder="Entez un prix">
                        <input type="number" style="width: 200px; margin-left: 180px; margin-top: -55px; float: left;" class="form-control" name="price-2" placeholder="Entez un prix">
                        </label>
                        
                    </div>
                    <button type="submit" style="margin-top: -130px; margin-left: 470px;" class="btn btn-primary">Submit</button>
                </form>                        

            </div>
    </div>
</body>
</html>
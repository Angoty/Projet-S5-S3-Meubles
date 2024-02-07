<%@page import="Models.components.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/form-style.css">
    <title>Materiel - Form</title>
</head>
<body>
    <div class="wrapper">
        <%@ include file='side-bar.jsp'%>

        <!-- Page Content  -->
        <div id="content">

            <%@ include file='nav.jsp'%>

            <div class="form" style="">
                <h3 style="text-align: center; padding-top: 50px;">Nouveau matériel:</h3>
                    <form action="./InsertMaterielController" method="post">
                            <div class="form-group">
                                <label for="Date" style="float: left;">Intitule :
                                    <input type="text" class="form-control" name="intitule" placeholder="Entrez le nom du materiel">
                                </label>
                            </div>
                            <div class="form-group">
                                <label for="quantity" style="float: left;">Prix :
                                    <input type="number" class="form-control" name="price" placeholder="Entrez le prix du materiel">
                                </label>
                                
                            </div>
                             
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form> 
                                 
            </div>
            <% if (request.getAttribute("error") != null) { %>
                <% String error = (String) request.getAttribute("error"); %>
                <div class="alert alert-danger" style="width:150px ; margin-left: 500px;">
                    <%= error %>
                </div>
            <% } %> 
        </div>
    </div>
</body>
</html>
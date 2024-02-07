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
                <h3 style="text-align: center; padding-top: 50px;">Informations du client:</h3>
                <form action="./InsertClientController" method="post">

                    <div class="form-group">
                        <label for="name" style="float: left;">Nom: 
                            <input type="text" class="form-control" name="name" placeholder="Nom du client">
                        </label>
                        
                    </div>
                    <div class="form-group">
                        <label for="meuble" style="float:left;">Genre: 
                            <select class="form-control" name="gender">
                                <% if (request.getAttribute("all_gender") != null) {
                                    Vector<String> genders = (Vector<String>) request.getAttribute("all_gender");
                                    for (int i = 0; i < genders.size(); i++) { %>
                                        <option value="<%= genders.get(i) %>"><%= genders.get(i) %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                       
                    </div>
                    <div class="form-group" style="float: left;">
                        <label for="quantity">Budget: 
                            <input type="number" class="form-control" name="budget" placeholder="Entez son budget">
                        </label>
                        
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>  
            </div>
                      
        </div>
    </div>
</body>
</html>
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
                <h3 style="text-align: center; padding-top: 50px;">Delai par grade</h3>
                <form action="./DelaiExperienceController" method="post">

                    <div class="form-group">
                        <label for="poste">Grade:
                        <select class="form-control" name="selected-poste">
                            <% if (request.getAttribute("all_postes") != null) {
                                Poste[] postes = (Poste[]) request.getAttribute("all_postes");
                                for (int i = 0; i < postes.length; i++) { %>
                                    <option value="<%= postes[i].getId() %>"><%= postes[i].getTypePoste() %></option>
                                <% } %>
                            <% } %>
                        </select>
                    </label>
                    </div>


                    <div class="form-group">
                        <label for="quantity">Delai:
                            <input type="number" class="form-control" name="delai" placeholder="Nombre d' annees">
                        </label>
                        
                    </div>

                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>  
            </div>
                      
        </div>
    </div>
</body>
</html>
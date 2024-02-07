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
                <h3 style="text-align: center; padding-top: 50px;">Prix du meuble</h3>
                <form action="./InsertMeublePrixController" method="post">
                    <div class="form-group">
                        <label for="meuble" style="float: left;">Categorie
                            <select class="form-control" name="selected-meuble">
                                <% if (request.getAttribute("all_meubles") != null) {
                                    Meuble[] meubles = (Meuble[]) request.getAttribute("all_meubles");
                                    for (int i = 0; i < meubles.length; i++) { %>
                                        <option value="<%= meubles[i].getId() %>"><%= meubles[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                        
                    </div>

                    <div class="form-group">
                        <label for="meuble" style="float: left;">Volume:
                            <select class="form-control" name="selected-volume" style="float: left;">
                                <% if (request.getAttribute("all_volumes") != null) {
                                    Volume[] volumes = (Volume[]) request.getAttribute("all_volumes");
                                    for (int i = 0; i < volumes.length; i++) { %>
                                        <option value="<%= volumes[i].getId() %>"><%= volumes[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="float: left; margin-top: 10px;">
                        <label for="quantity">Prix:
                            <input type="number" class="form-control" name="prix" placeholder="Entez le prix">
                        </label>
                        
                    </div>

                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>     
            </div>
                   
        </div>
    </div>
</body>
</html>
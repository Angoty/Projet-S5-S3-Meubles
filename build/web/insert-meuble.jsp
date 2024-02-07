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
                <h3 style="text-align: center; padding-top: 50px;">Nouveau meuble</h3>
                <form action="./InsertMeubleController" method="post">
                    <div class="form-group">
                        <label for="quantity" style="float: left;">Categorie
                            <input type="text" class="form-control" name="intitule" placeholder="Intitule">
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="meuble" style="float: left;">Style:
                            <select class="form-control" name="selected-style">
                                <% if (request.getAttribute("all_styles") != null) {
                                    Style[] styles = (Style[]) request.getAttribute("all_styles");
                                    for (int i = 0; i < styles.length; i++) { %>
                                        <option value="<%= styles[i].getId() %>"><%= styles[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                        
                    </div>

                    <div class="form-group">
                        <label for="meuble" style="float: left; ">Volume:
                            <select class="form-control" name="selected-volume">
                                <% if (request.getAttribute("all_volumes") != null) {
                                    Volume[] volumes = (Volume[]) request.getAttribute("all_volumes");
                                    for (int i = 0; i < volumes.length; i++) { %>
                                        <option value="<%= volumes[i].getId() %>"><%= volumes[i].getIntitule() %></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </label>
                       
                    </div>

                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>    
            </div>
                    

            </div>
    </div>
</body>
</html>
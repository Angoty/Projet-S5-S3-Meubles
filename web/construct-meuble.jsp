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
                <h3 style="text-align: center; padding-top: 50px;">Nouveau meuble - stock</h3>
                <form action="./ConstructMeubleController" method="post">
                    <div class="form-group">
                        <label for="meuble">Choose a Meuble:
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
                    <label for="Date" style="float: left;">Date:
                        <input type="date" class="form-control" name="date-stock" placeholder="Enter a Date">
                    </label>
                    </div>

                    <div class="form-group">
                    <label for="quantity" style="float: left;">Quantity:
                        <input type="number" class="form-control" name="qte" placeholder="Entez la quantite">
                    </label>
                   
                    </div>

                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>        
            </div>
               
        </div>

    </div>
</body>
</html>
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
            <h3 style="text-align: center; padding-top: 50px;">Situation des employés:</h3>
                    <form action="./SituationPersonController" method="post">
                        <div class="form-group">
                            <label for="quantity">Date :
                                <input type="date" class="form-control" name="date" placeholder="Enter a date" style="width: 150px;">
                            </label>
                        </div>
        
                        <button type="submit" style="margin-top: -140px;" class="btn btn-primary">Voir</button>
                    </form>   
                    <% 
                    if (request.getAttribute("all_mvt") != null) {
                        MvtPoste[] all = (MvtPoste[]) request.getAttribute("all_mvt");
                %>
                    <table class="table" style="width: 40%;">
                        <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Poste</th> 
                                <th>Salaire</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < all.length; i++) { %>
                                <tr>
                                    <td><%= new Person().getSpecificPerson(all[i].getIdPerson()).getIntitule() %></td>
                                    <td><%= new Poste().getSpecificPoste(all[i].getIdNewPost()).getTypePoste() %></td>
                                    <td><%=  all[i].getNewSalary() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>     
        </div>
    </div>
</body>
</html>
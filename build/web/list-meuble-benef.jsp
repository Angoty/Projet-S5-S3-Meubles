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


            <h2 class="mb-4">List Meuble</h2>
            <% 
                if (request.getAttribute("meuble-benef") != null) {
                    Vector<Meuble> meubles = (Vector<Meuble>) request.getAttribute("meuble-benef");
            %>

                <table class="table" style="width: 40%;">
                    <thead>
                        <tr>
                            <th>Meuble</th>
                            <th>Benef</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i = 0; i < meubles.size(); i++) { %>
                            <tr>
                                <td><%= meubles.get(i).getIntitule() %></td>
                                <td><%= meubles.get(i).getBenef() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>

            <% } %>
        </div>
    </div>
</body>
</html>
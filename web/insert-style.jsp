<%@page import="Models.components.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/form-style.css">
    <title>Style - Form</title>
</head>
<body>
    <div class="wrapper">
        <%@ include file='side-bar.jsp'%>

        <!-- Page Content  -->
        <div id="content">

            <%@ include file='nav.jsp'%>
            <div class="form" style="">
                <h3 style="text-align: center; padding-top: 50px;">Nouveau style de meuble</h3>
                <form action="./InsertStyleController" method="post">
                    <div class="form-group">
                        <label for="quantity" style="float: left;">Intitule:
                            <input type="text" class="form-control" name="name-style" placeholder="Entez le nom du style">
                        </label>
                        
                    </div>
                    <label style="float: left;">Materiaux nécéssaires:
                        <%  if ( request.getAttribute("all_materiels") != null) {
                            Materiel[] materiels = (Materiel[]) request.getAttribute("all_materiels");
                            for (int i = 0; i < materiels.length; i++) { %>
                                <div>
                                    <input class="form-check-input" style="margin-left:-740px; margin-top:5px;" type="checkbox" name="selected-materiels" value="<%= materiels[i].getId() %>">
                                    <label class="form-check-label" for="interest1" style=" font-weight:200px;">  <%= materiels[i].getIntitule() %></label>
                                </div> 
                                <% } %>
                                <%  }  %>
                    </label>    
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>  
            </div>
        </div>    
    </div>
</body>
</html>
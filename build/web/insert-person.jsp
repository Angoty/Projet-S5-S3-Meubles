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
                <h3 style="text-align: center; padding-top: 50px;">Nouveau employé</h3>
                <form action="./InsertPersonController" method="post">

                    <div class="form-group">
                        <label for="quantity" style="float:left;">Nom :
                            <input type="text" class="form-control" name="name" placeholder="Enter a type worker">
                        </label>
                    </div>
                    <div class="form-group" style="float: left;">
                        <label for="quantity">Date d'embauche:
                            <input type="date" style="width: 150px; margin-left: 200px;" class="form-control" name="date" placeholder="Enter a date">
                        </label>
                        
                    </div>

                    <div class="form-group">
                        <label for="quantity" style="float: left;">Salaire :
                            <input type="number" class="form-control" name="salary" placeholder="Enter salary">
                        </label>
                        
                    </div>

                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>        
            </div>
                
        </div>
    </div>
</body>
</html>
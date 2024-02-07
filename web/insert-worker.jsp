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
                <h3 style="text-align: center; padding-top: 50px;">Nouveau poste</h3>
                <form action="./InsertWorkerController" method="post">

                    <div class="form-group">
                        <label for="quantity">Poste:
                            <input type="text" class="form-control" name="type" placeholder="Enter a type worker">
                        </label>
                        
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>        
            </div>
        </div>
    </div>
</body>
</html>
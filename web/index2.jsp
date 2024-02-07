<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meuble</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <style>
            .basic-1 {
                padding-top: -100rem;
                padding-bottom: 0rem;
            }

            .basic-1 .text-container {
                margin-bottom: 4rem;
            }

            .basic-1 h2 {
                margin-bottom: 1.875rem;
            }

            .basic-1 p {
                margin-bottom: 1.875rem;
            }

            .basic-1 .btn-solid-reg {
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%@ include file='side-bar.jsp'%>
    
            <!-- Page Content  -->
            <div id="content">
    
                <%@ include file='nav.jsp'%>

    
                <center>
                    <% if (request.getAttribute("error") != null) { %>
                        <% String error = (String) request.getAttribute("error"); %>
                        <div class="alert alert-danger">
                            <%= error %>
                        </div>
                    <% } %>


                    <div id="details" class="basic-1 bg-gray">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-6 col-xl-5">
                                    
                                </div> 
                                <div class="col-lg-6 col-xl-7">
                                    <div class="image-container">
                                        <img class="img-fluid" src="./assets/img/details-1.svg" ">
                                    </div> 
                                </div> 
                            </div> 
                        </div> 
                    </div> 
                </center>
            </div>
        </div>
    </body>
</html>
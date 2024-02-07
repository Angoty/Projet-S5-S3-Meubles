<%@page import="Models.components.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    AchatMeuble[] all = null;

    Map<String, Integer> achatParGenre = null;
    Map<String, Integer> achatParStyle = null;

    Style[] allStyle = new Style().getAll(Style.class, null);

    if (request.getAttribute("all-achat") != null && request.getAttribute("all-achat-genre") != null && request.getAttribute("all-achat-style") != null) {
        all = (AchatMeuble[]) request.getAttribute("all-achat");
        achatParGenre = (Map<String, Integer>) request.getAttribute("all-achat-genre");
        achatParStyle = (Map<String, Integer>) request.getAttribute("all-achat-style");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="./assets/form-style.css">
    <link rel="stylesheet" href="./assets/table-style.css">
    <title>Document</title>
</head>
<body>
    <div class="wrapper">
        <%@ include file='side-bar.jsp'%>        
        
        <!-- Page Content  -->
        <div id="content">
           
            <%@ include file='nav.jsp'%>
            <% if (all != null && achatParGenre != null && achatParStyle != null) { %>
                
                <form action="./TableauStatController" method="post" class="form-container">

                    <div class="form-group">
                        
                        <select class="form-control" name="selected-style" style="width: 20%; float: left;">
                            <% for (int i = 0; i < allStyle.length; i++) { %>
                                <option value="<%= allStyle[i].getIntitule() %>"><%= allStyle[i].getIntitule() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="selected-gender" style="width: 20%; float: left;">
                            <% for (Map.Entry<String, Integer> entry : achatParGenre.entrySet()) { %>
                                <option value="<%= entry.getKey() %>"><%= entry.getKey() %></option>
                            <% } %>
                        </select>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                    
                    <canvas id="barChart" style="max-width: 500px; max-height: 300px; float: left;"></canvas>
                    <canvas id="pieChart" style="max-width: 500px; max-height: 300px; "></canvas>
                    <table class="table" style="width: 20%; margin-left:950px; margin-top: -200px;">
                        <thead>
                            <tr>
                                <th>Gender</th>
                                <th>Number</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Map.Entry<String, Integer> entry : achatParGenre.entrySet()) { %>
                                <tr>
                                    <td><%= entry.getKey() %></td>
                                    <td><%= entry.getValue() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table> 
                    
                </form> <br> <br>

                <h2 class="mb-4">Tableau d'achat des Clients</h2>
                <table class="table" style="width: 140%; margin-top: 100px;">
                    <thead>
                        <tr>
                            <th>Client</th>
                            <th>Meuble</th> 
                            <th>Style</th> 
                            <th>Quantité</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i = 0; i < all.length; i++) { %>
                            <tr>
                                <td><%= all[i].getClient().getIntitule() %></td>
                                <td><%= all[i].getMeuble().getIntitule() %></td>
                                <td><%= all[i].getMeuble().getStyle().getIntitule() %></td>
                                <td><%= all[i].getQuantite() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table> <br> <br> <br>    
                
            <% } %>
            <% if (request.getAttribute("error") != null) { %>
                <% String error = (String) request.getAttribute("error"); %>
                <div class="alert alert-danger">
                    <%= error %>
                </div>
            <% } %>
        </div>
    </div>

    <script>
        // retrieve the data
        var barChartLabels = [];
        var barChartDataArray = [];

        <% for (Map.Entry<String, Integer> entry : achatParStyle.entrySet()) { %>
            barChartLabels.push('<%= entry.getKey() %>');
            barChartDataArray.push('<%= entry.getValue() %>');
        <% } %>

        var barChartData = {
            labels: barChartLabels,
            datasets: [{
                label: 'Quantité',
                data: barChartDataArray,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        };

        var pieChartLabels = [];
        var pieChartDataArray = [];

        <% for (Map.Entry<String, Integer> entry : achatParGenre.entrySet()) { %>
            pieChartLabels.push('<%= entry.getKey() %>');
            pieChartDataArray.push('<%= entry.getValue() %>');
        <% } %>

        var pieChartData = {
            labels: pieChartLabels,
            datasets: [{
                data: pieChartDataArray,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                ],
                borderWidth: 1
            }]
        };


        // Init the bar chart
        var barChartCtx = document.getElementById('barChart').getContext('2d');
        var barChart = new Chart(barChartCtx, {
            type: 'bar',
            data: barChartData,
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                width: 200, 
                height: 200 
            }
        });

        // Init the pie chart
        var pieChartCtx = document.getElementById('pieChart').getContext('2d');
        var pieChart = new Chart(pieChartCtx, {
            type: 'pie',
            data: pieChartData,
            options: {
                width: 200, 
                height: 200 
            }
        });
    </script>
     <% if (request.getAttribute("error") != null) { %>
        <% String error = (String) request.getAttribute("error"); %>
        <div class="alert alert-danger">
            <%= error %>
        </div>
    <% } %>
</body>
</html>
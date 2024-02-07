{/* <script>
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
    </script> */}
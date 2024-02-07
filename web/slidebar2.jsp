<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<!-- Custom CSS -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/assets/side-bar-style.css">

<!-- Font Awesome JS -->
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>

<!-- Sidebar  -->
<nav id="sidebar">
    <div class="sidebar-header">
        <h3>Gestion Meuble</h3>
    </div>

    <ul class="list-unstyled components">
        <p>Hello World</p>

        <li>
            <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Confection</a>
            <ul class="collapse list-unstyled" id="pageSubmenu">
               <li>
                    <a href="./insert-materiel.jsp">Nouveau Materiel</a>
               </li>

               <li>
                    <a href="./insert-volume.jsp">Insert Volume</a>
               </li>

               <li>
                    <a href="./InsertStockController">Insert Stock</a>
               </li>

               <li>
                    <a href="./insert-worker.jsp">Insert Worker</a>
               </li>

               <li>
                    <a href="./GetMaterielController">Insert Style</a>
               </li>

               <li>
                    <a href="./GetStyleController">Insert Meuble</a>
               </li>

               <li>
                    <a href="./GetMeubleController">Insert Meuble Material Quantity</a>
               </li>

               <li>
                    <a href="./InsertMeublePrixController">Insert Meuble Prix Achat</a>
               </li>

               <li>
                    <a href="./InsertMeubleWorkerController">Insert Meuble Worker</a>
               </li>
                <li>
                    <a href="./insert-poste.jsp">Insert Poste</a>
                </li>

                <li>
                    <a href="./insert-person.jsp">Insert Person</a>
                </li>

                <li>
                    <a href="./DelaiExperienceController">Insert Poste Delai</a>
                </li>

                <li>
                    <a href="./PosteSalaryController">Insert Poste Salary</a>
                </li>

                <li>
                    <a href="./InsertClientController">Insert Client</a>
                </li>
            </ul>
        </li>


        <li>
            <a href="#listSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">List</a>
            <ul class="collapse list-unstyled" id="listSubmenu">
                <li>
                    <a href="./GetStockController">List stock</a>
                </li>

                <li>
                    <a href="./TableauStatController">Statistiques</a>
                </li>
            </ul>
        </li>


        <li>
            <a href="#specificSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Specific</a>
            <ul class="collapse list-unstyled" id="specificSubmenu">
                <li>
                    <a href="./GetMaterialSearchController">Find specific Meuble</a>
                </li>

                <li>
                    <a href="./filter-meuble.jsp">Filter Meuble by price</a>
                </li>

                <li>
                    <a href="./ConstructMeubleController">Construct Meuble</a>
                </li>

                <li>
                    <a href="./meuble-prix-achat.jsp">Filter Meuble Worker</a>
                </li>

                <li>
                    <a href="./situation-person.jsp">Situation employee</a>
                </li>

                <li>
                    <a href="./BuyMeubleController">Buy Meuble</a>
                </li>
            </ul>
        </li>

    </ul>

    <ul class="list-unstyled CTAs">
        <li>
            <a href="https://bootstrapious.com/tutorial/files/sidebar.zip" class="download">Download source</a>
        </li>
        <li>
            <a href="https://bootstrapious.com/p/bootstrap-sidebar" class="article">Back to article</a>
        </li>
    </ul>
</nav>       

<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

<script type="text/javascript">
$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
});
</script>
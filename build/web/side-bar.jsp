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
        <h3>Meuble</h3>
    </div>
    <ul class="list-unstyled components">

        <li>
            <a href="#critereSubmenu" data-toggle="collapse" aria-expanded="false">Caracteristique du meuble</a>
            <ul class="collapse list-unstyled" id="critereSubmenu">
               <li>
                    <a href="./insert-materiel.jsp">Nouveau materiel</a>
               </li>
               <li>
                    <a href="./GetMaterielController">Nouveau style de meuble</a>
                </li>
               <li>
                    <a href="./insert-volume.jsp">Nouveau type de volume</a>
               </li>
               <li>
                    <a href="./insert-categorie.jsp">Nouveau categorie</a>
                </li>
            </ul>
        </li>
        <!--  -->
        <li>
            <a href="#confecSubmenu" data-toggle="collapse" aria-expanded="false">Confection de meuble</a>
            <ul class="collapse list-unstyled" id="confecSubmenu">
                <li>
                    <a href="./InsertStockController">Stock de materiel</a>
                </li>
                <li>
                    <a href="./GetMeubleController">Quantite de materiaux necessaires</a>
                </li>
                <li>
                    <a href="./InsertMeublePrixController">Prix meuble</a>
                </li>
                <li>
                    <a href="./GetStyleController">Nouveau meuble</a>
               </li>
               
               <li>
                    <a href="./InsertMeubleWorkerController">Personnels Meuble</a>
                </li>
                <li>
                    <a href="./ConstructMeubleController">Nouveau stock de meuble</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#personnelSubmenu" data-toggle="collapse" aria-expanded="false">Gestion des personnels</a>
            <ul class="collapse list-unstyled" id="personnelSubmenu">
                <li>
                    <a href="./insert-poste.jsp">Grade</a>
                </li>
                <li>
                    <a href="./DelaiExperienceController">Delai</a>
                </li>
                <li>
                    <a href="./PosteSalaryController">Salaire</a>
                </li>
                <li>
                    <a href="./insert-worker.jsp">Nouveau Poste</a>
                </li>
                <li>
                    <a href="./insert-person.jsp">Nouveau Personnel</a>
                </li>
                <li>
                    <a href="./situation-person.jsp">Situation des personnels</a>
                </li>
            </ul>
        </li>
        
       
        <li>
            <a href="#clientSubmenu" data-toggle="collapse" aria-expanded="false">Gestion des clients</a>
            <ul class="collapse list-unstyled" id="clientSubmenu">
                <li>
                    <a href="./InsertClientController">Nouveau client</a>
                </li>
                <li>
                    <a href="./BuyMeubleController">Achat meuble</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#specificSubmenu" data-toggle="collapse" aria-expanded="false">Liste avancee</a>
            <ul class="collapse list-unstyled" id="specificSubmenu">
                <li>
                    <a href="./GetMaterialSearchController">Liste des meubles finis</a>
                </li>
                <li>
                    <a href="./filter-meuble.jsp">Liste des meubles avec prix</a>
                </li>
                <li>
                    <a href="./meuble-prix-achat.jsp">Benefice</a>
                </li>
                <li>
                    <a href="./GetStockController">Etat de stock de materiel</a>
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
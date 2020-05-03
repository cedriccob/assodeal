
<?php

    include "header.php";
    include "register-login.php";
    include "main-menu.php";



    if (isset($_SESSION['login']) && isset($_SESSION['role']) && $_SESSION['role']==="adm") {
        echo "<div class='container'><p>Bienvenue admin ".$_SESSION['login']."</p></div>";
?>
<div class="col-md-3 column margintop20">
    <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
        <li id="li-att-produit"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Attributs produit</a></li>
        <li id="li-cat-produit"><a href="#" class="active2"><span class="glyphicon glyphicon-chevron-right"></span> Catégories produit</a></li>
        <li id="li-commande"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Commandes</a></li>
        <li id="li-facture"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Factures</a></li>
        <li id="li-produit"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Produits</a></li>
        <li id="li-role"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Roles</a></li>
    </ul>
</div>

<?php

include "attribut-produit/table.php";
include "categorie-produit/table.php";
include '../view/footer.php';
?>
<script src="../public/js/admin.js"></script>
<script src="../public/js/attributs_produit.js"></script>
<script src="../public/js/categorie_produit.js"></script>
<?php
    }
    else{
        header("Location:/assodeal/view/login.php");
    }
?>

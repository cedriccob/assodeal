<?php

    include "header.php";
    include "register-login.php";
    include "main-menu.php";

    if (isset($_SESSION['login']) && isset($_SESSION['role']) && $_SESSION['role']==="adm"){
        echo "<div class='container'><p>Bienvenue admin ".$_SESSION['login']."</p></div>";
    }

include '../view/footer.php';



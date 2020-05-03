<?php
include ("../session.php");

require_once('../../model/CategorieProduit.php');
if(isset($_POST["cat_produit_id"]))
{

    $catProduit = new CategorieProduit();
    $id=$_POST["cat_produit_id"];
    $token=$_SESSION['tokenUser'];
    $result = $catProduit->deleteCategorie($id,$token);

    if($result=="200")
    {
        echo 'Les données ont été supprimées';
    }
}
?>
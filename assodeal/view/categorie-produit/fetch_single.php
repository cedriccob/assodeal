<?php
include ("../session.php");
require_once('../../model/CategorieProduit.php');
if(isset($_POST["cat_produit_id"]))
{
    $output = array();
    $catProduit = new CategorieProduit();
    $id=$_POST["cat_produit_id"];
    $token=$_SESSION['tokenUser'];
    $result = $catProduit->getCategorieProduitById($id,$token);
    $output["libelle"] = $result["libelleCategorieProduit"];
    echo json_encode($output);
}
?>
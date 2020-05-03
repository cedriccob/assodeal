<?php
include ("../session.php");
require_once('../../model/AttributsProduit.php');
if(isset($_POST["att_produit_id"]))
{
    $output = array();
    $attProduit = new AttributsProduit();
    $id=$_POST["att_produit_id"];
    $token=$_SESSION['tokenUser'];
    $result = $attProduit->getAttributeById($id,$token);
    $output["couleur"] = $result["couleurProduit"];
    $output["poids"] = $result["poidsProduit"];
    $output["abreviation"] = $result["abreviationProduit"];

    echo json_encode($output);
}
?>
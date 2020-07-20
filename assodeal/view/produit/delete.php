<?php
include ("../session.php");

require_once('../../model/Produit.php');
if(isset($_POST["produit_id"]))
{

    $produit = new Produit();
    $id=$_POST["produit_id"];
    $token=$_SESSION['tokenUser'];
    $result = $produit->deleteProduit($id,$token);

    if($result=="200")
    {
        echo 'Les données ont été supprimées';
    }
}
?>
<?php
include ("../session.php");


require_once('../../model/AttributsProduit.php');
if(isset($_POST["att_produit_id"]))
{

    $attProduit = new AttributsProduit();
    $id=$_POST["att_produit_id"];
    $token=$_SESSION['tokenUser'];
    $result = $attProduit->deleteAttributs($id,$token);


    if($result=="200")
    {
        echo 'Les données ont été supprimées';
    }
}
?>
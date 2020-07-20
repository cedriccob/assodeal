<?php
include ("../session.php");
require_once('../../model/Produit.php');
if(isset($_SESSION['tokenUser'])) {
    if (isset($_POST["produit_id"])) {
        $output = array();
        $produit = new Produit();
        $id = $_POST["produit_id"];
        $token = $_SESSION['tokenUser'];
        $result = $produit->getProduitById($id, $token);
        $output["libelle"] = $result["libelleProduit"];
        $output["stock"] = $result["qtStockProduit"];
        $output["prixReel"] = $result["prixReelProduit"];
        $output["prixVente"] = $result["prixVenteProduit"];
        $output["detail"] = $result["detailProduit"];
        $output["idCategorie"] = $result["categorieProduit"]{"idCategorieProduit"};
        $output["couleur"] = $result["couleurProduit"];
        $output["poids"] = $result["poidsProduit"];
        $output["abreviation"] = $result["abreviationProduit"];
        $output['produit_image']= $result["imageProduit"];

        echo json_encode($output);
    }
    else{
        echo "erreur de récupération de l'id";
    }
}
else{
    echo "erreur de récupération du token";
}
?>
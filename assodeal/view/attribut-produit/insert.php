<?php
include("../session.php");

require_once('../../model/AttributsProduit.php');
if(isset($_SESSION['tokenUser'])) {
    $token = $_SESSION['tokenUser'];
    if (isset($_POST["operation_att"])) {
        $operation = $_POST["operation_att"];
        $attribut = new AttributsProduit();
        $result = "";
        if (isset($_POST["couleur"]) && isset($_POST["poids"]) && isset($_POST["abreviation"])) {
            $couleur = $_POST["couleur"];
            $poids = $_POST["poids"];
            $abreviation = $_POST["abreviation"];
            if ($couleur != "" && $poids != "" && $abreviation != "") {
                if ($operation == "Add") {
                    $att = new AttributsProduit();
                    $att->setCouleur($couleur);
                    $att->setPoids($poids);
                    $att->setAbreviation($abreviation);
                    $statusRes = $attribut->saveAttributs($att, $token);
                    if ($statusRes == "201") {
                        $result = "Les données sont insérées avec succès";
                    } else {
                        $result = "Erreur lors de l'insertion des données";
                    }
                } else if ($operation == "Edit" && isset($_POST["att_produit_id"])) {
                    $att = new AttributsProduit();
                    $att->setCouleur($couleur);
                    $att->setPoids($poids);
                    $att->setAbreviation($abreviation);
                    $id = $_POST["att_produit_id"];
                    if ($id != "") {
                        $statusRes = $attribut->updateAttributs($att, $token, $id);
                         if ($statusRes == "200") {
                            $result = "Les données ont été mises à jour";
                         } else {
                            $result = "Erreur lors de la mise à jour des données";
                        }
                    } else {
                        $result = "Erreur de récupération de l'id";
                    }
                } else {
                    $result = "Erreur de récupération de l'opération";
                }
            } else {
                $result = "Tous les champs sont obligatoires";
            }
        } else {
            $result = "Erreur de récupération des champs du formulaire";
        }
    } else {
        $result = "Erreur de récupération de l'opération";
    }
}
else{
    $result = "Erreur de récupération du token";
}
echo $result;
?>
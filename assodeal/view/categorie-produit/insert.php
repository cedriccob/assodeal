<?php
include("../session.php");
require_once('../../model/CategorieProduit.php');
if(isset($_SESSION['tokenUser'])) {
    $token = $_SESSION['tokenUser'];
    if (isset($_POST["operation_cat"])) {
        $operation = $_POST["operation_cat"];
        $categorie = new CategorieProduit();
        $result = "";
        if (isset($_POST["libelle"])) {
            $libelle = $_POST["libelle"];
            if ($libelle != "") {
                if ($operation == "Add") {
                    $cat = new CategorieProduit();
                    $cat->setLibelle($libelle);
                    $statusRes = $categorie->saveCategorie($cat, $token);
                    if ($statusRes == "201") {
                        $result = "Les données sont insérées avec succès";
                    } else {
                        $result = "Erreur lors de l'insertion des données";
                    }
                } else if ($operation == "Edit" && isset($_POST["cat_produit_id"])) {
                    $cat = new CategorieProduit();
                    $cat->setLibelle($libelle);
                    $id = $_POST["cat_produit_id"];
                    if($id!="") {
                        $statusRes = $categorie->updateCategorie($cat, $token, $id);
                        if ($statusRes == "200") {
                            $result = "Les données ont été mises à jour";
                        } else {
                            $result = "Erreur lors de la mise à jour des données";
                        }
                    }
                    else{
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
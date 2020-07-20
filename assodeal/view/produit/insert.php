<?php
include("../session.php");
require_once('../../model/Produit.php');
require_once('../../model/CategorieProduit.php');
function upload_image()
{
    if(isset($_FILES["produit_image"]))
    {
        $extension = explode('.', $_FILES['produit_image']['name']);
        $new_name = rand() . '.' . $extension[1];
        $destination = './upload/' . $new_name;
        move_uploaded_file($_FILES['produit_image']['tmp_name'], $destination);
        return $new_name;
    }
}

if(isset($_SESSION['tokenUser'])) {
    $token = $_SESSION['tokenUser'];
    if (isset($_POST["operation_pdt"])) {
        $operation = $_POST["operation_pdt"];
        $categ = new CategorieProduit();
        $produit = new Produit();
        $result = "";
        $image="";
        if (isset($_POST["libelle"]) && isset($_POST["quantite"]) && isset($_POST["prixReel"]) && isset($_POST["prixVente"]) && isset($_POST["detail"])
        && isset($_POST["couleur"]) && isset($_POST["poids"]) && isset($_POST["abreviation"]) && isset($_POST["selectCategorie"]) && isset($_FILES["produit_image"]["name"])) {
            $libelle = $_POST["libelle"];
            $quantite = $_POST["quantite"];
            $prixReel = $_POST["prixReel"];
            $prixVente = $_POST["prixVente"];
            $categorie = $_POST["selectCategorie"];
            $detail = $_POST["detail"];
            $couleur = $_POST["couleur"];
            $poids = $_POST["poids"];
            $abreviation = $_POST["abreviation"];

           // $image = $_FILES["produit_image"];
            $nameProduitImage = $_FILES['produit_image']['name'];
            $target_dir = 'upload/';
            //Check if the directory already exists.
            if(!is_dir($target_dir)){
                //Directory does not exist, so lets create it.
                mkdir($target_dir, 0755);
            }
            $target_file = $target_dir . basename($_FILES["produit_image"]["name"]);

            // Select file type
            $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

            // Valid file extensions
            $extensions_arr = array("jpg","jpeg","png","gif");

            // Check extension
            if( in_array($imageFileType,$extensions_arr) ){
                // Convert to base64
                $image_base64 = base64_encode(file_get_contents($_FILES['produit_image']['tmp_name']));
                $image = 'data:image/'.$imageFileType.';base64,'.$image_base64;
                // Upload file
               // move_uploaded_file($_FILES['produit_image']['tmp_name'],$target_dir.$nameProduitImage);
            }
            $catObject = $categ->getCategorieProduitById($categorie, $token);
            if ($libelle != "" && $quantite != "" && $prixReel != "" && $prixVente != "" && $categorie != "") {
                if ($operation == "Add") {
                    $cat = new CategorieProduit();
                    $cat->setId($categorie);
                    $pdt = new Produit();
                    $pdt->setLibelle($libelle);
                    $pdt->setQuantite($quantite);
                    $pdt->setPrixReel($prixReel);
                    $pdt->setPrixVente($prixVente);
                    $pdt->setCouleur($couleur);
                    $pdt->setDetail($detail);
                    $pdt->setPoids($poids);
                    $pdt->setAbreviation($abreviation);
                    $pdt->setCategorie($categorie);
                    $pdt->setImage(addslashes(file_get_contents($_FILES['produit_image']['tmp_name'])));
                    $statusRes = $produit->saveProduitMultiPart($pdt, $token);
                    if ($statusRes == "201") {
                        $result = "Les données sont insérées avec succès";
                    } else {
                        $result = "Erreur lors de l'insertion des données";
                    }
                } else if ($operation == "Edit" && isset($_POST["produit_id"])) {
                    $id = $_POST["produit_id"];
                    $pdt = new Produit();
                    $pdt->setLibelle($libelle);
                    $pdt->setQuantite($quantite);
                    $pdt->setPrixReel($prixReel);
                    $pdt->setPrixVente($prixVente);
                    $pdt->setCouleur($couleur);
                    $pdt->setDetail($detail);
                    $pdt->setPoids($poids);
                    $pdt->setAbreviation($abreviation);
                    $pdt->setCategorie($catObject);
                    $pdt->setImage($image_base64);
                    if($id!="") {
                        $statusRes = $produit->updateProduit($pdt, $token, $id);
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
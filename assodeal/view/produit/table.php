<?php
require_once('../model/CategorieProduit.php');
?>
<div id="produit" class="container box">
    <h1 align="center">Produit</h1>
    <br />
    <div class="table-responsive">
        <br />
        <div align="right">
            <button type="button" id="add_button_pdt" data-toggle="modal" data-target="#produitModal" class="btn btn-info btn-lg">Add</button>
        </div>
        <br /><br />
        <table id="produit_data" class="table table-bordered table-striped" style="width: 100%">
            <thead>
            <tr>
                <th>Libellé</th>
                <th>Quantité stock</th>
                <th>Prix réel</th>
                <th>Prix vente</th>
                <th>Détail</th>
                <th>Couleur</th>
                <th>Poids</th>
                <th>Abréviation</th>
                <th>Catégorie</th>
                <th>Image</th>
                <th width="10%">Edit</th>
                <th width="10%">Delete</th>
            </tr>
            </thead>
        </table>

    </div>
</div>

<div id="produitModal" class="modal fade">
    <div class="modal-dialog">
        <form method="post" id="produit_form" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Ajouter un produit</h4>
                </div>
                <div class="modal-body">
                    <label>Entrez un libellé</label>
                    <input type="text" name="libelle" id="libelle" class="form-control" />
                    <br />
                    <label>Entrez la quantité en stock</label>
                    <input type="text" name="quantite" id="quantite" class="form-control" />
                    <br />
                    <label>Entrez le prix réel</label>
                    <input type="text" name="prixReel" id="prixReel" class="form-control" />
                    <br />
                    <label>Entrez le prix de vente</label>
                    <input type="text" name="prixVente" id="prixVente" class="form-control" />
                    <br />
                    <label>Entrez le détail </label>
                    <input type="text" name="detail" id="detail" class="form-control" />
                    <br />
                    <label>Entrez une couleur</label>
                    <input type="text" name="couleur" id="couleur" class="form-control" />
                    <br />
                    <label>Entrez un poids</label>
                    <input type="text" name="poids" id="poids" class="form-control" />
                    <br />
                    <label>Entrez une abréviation de produit</label>
                    <input type="text" name="abreviation" id="abreviation" class="form-control" />
                    <label>Sélectionnez la catégorie de produit</label>
                    <select class="selectpicker" name="selectCategorie" id="selectCategorie">
                    <?php
                        $categorieProduit = new CategorieProduit();
                        $result = $categorieProduit->getAllCategories();
                        foreach ($result as $key => $value) {
                            echo "<option value = '" . $value["idCategorieProduit"] . "'>" . $value["libelleCategorieProduit"] . "</option >";
                        }
                    ?>
                    <br />
                    <label>Selectionnez l'image du produit</label>
                    <input type="file" name="produit_image" id="produit_image" />

                </div>


                </div>
                <div class="modal-footer">
                    <input type="hidden" name="produit_id" id="produit_id" />
                    <input type="hidden" name="operation_pdt" id="operation_pdt" />
                    <input type="submit" name="action_pdt" id="action_pdt" class="btn btn-success" value="Add" />
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>
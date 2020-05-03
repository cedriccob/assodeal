
<div id="categorie-produit" class="container box">
    <h1 align="center">Catégories produit</h1>
    <br />
    <div class="table-responsive">
        <br />
        <div align="right">
            <button type="button" id="add_button_cat" data-toggle="modal" data-target="#catProduitModal" class="btn btn-info btn-lg">Add</button>
        </div>
        <br /><br />
        <table id="cat_produit_data" class="table table-bordered table-striped" style="width: 100%">
            <thead>
            <tr>
                <th width="35%">Libellé</th>
                <th width="10%">Edit</th>
                <th width="10%">Delete</th>
            </tr>
            </thead>
        </table>

    </div>
</div>

<div id="catProduitModal" class="modal fade">
    <div class="modal-dialog">
        <form method="post" id="cat_produit_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Ajouter une catégorie de produit</h4>
                </div>
                <div class="modal-body">
                    <label>Entrez un libellé</label>
                    <input type="text" name="libelle" id="cat-libelle" class="form-control" />
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="cat_produit_id" id="cat_produit_id" />
                    <input type="hidden" name="operation_cat" id="operation_cat" />
                    <input type="submit" name="action_cat" id="action_cat" class="btn btn-success" value="Add" />
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div id="attribut-produit" class="container box">
    <h1 align="center">Attributs produit</h1>
    <br />
    <div class="table-responsive">
        <br />
        <div align="right">
            <button type="button" id="add_button_att" data-toggle="modal" data-target="#attProduitModal" class="btn btn-info btn-lg">Add</button>
        </div>
        <br /><br />
        <table id="att_produit_data" class="table table-bordered table-striped" style="width: 100%">
            <thead>
            <tr>
                <th width="35%">Couleur</th>
                <th width="35%">Poids</th>
                <th width="35%">Abréviation</th>
                <th width="10%">Edit</th>
                <th width="10%">Delete</th>
            </tr>
            </thead>
        </table>

    </div>
</div>

<div id="attProduitModal" class="modal fade">
    <div class="modal-dialog">
        <form method="post" id="att_produit_form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Ajouter un attribut de produit</h4>
                </div>
                <div class="modal-body">
                    <label>Entrez une couleur</label>
                    <input type="text" name="couleur" id="attr-couleur" class="form-control" />
                    <br />
                    <label>Entrez un poids</label>
                    <input type="text" name="poids" id="attr-poids" class="form-control" />
                    <br />
                    <label>Entrez une abréviation de produit</label>
                    <input type="text" name="abreviation" id="attr-abv" class="form-control" />
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="att_produit_id" id="att_produit_id" />
                    <input type="hidden" name="operation_att" id="operation_att" />
                    <input type="submit" name="action_att" id="action_att" class="btn btn-success" value="Add" />
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </form>
    </div>
</div>

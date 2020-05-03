$(document).ready(function(){
    function init() {
        if ($('#categorie-produit').attr("style") !== "display: none;") {
            $('#add_button_cat').click(function () {

                if ($('#cat_produit_form')[0]) {
                    $('#cat_produit_form')[0].reset();
                }
                $('.modal-title').text("Ajouter une catégorie de produit");
                $('#action_cat').val("Add");
                $('#operation_cat').val("Add");
            });


            var dataTable = $('#cat_produit_data').DataTable({
                "processing": true,
                "serverSide": true,
                "order": [],
                "ajax": {
                    url: "/assodeal/view/categorie-produit/fetch.php",
                    type: "POST"
                },
                "columnDefs": [
                    {
                        "targets": [1, 2],
                        "orderable": false,
                    }
                ]
            });

            $(document).on('submit', '#cat_produit_form', function (event) {
                event.preventDefault();
                var libelle = $('#cat-libelle').val();
                if (libelle !== '') {
                    $.ajax({
                        url: "/assodeal/view/categorie-produit/insert.php",
                        method: 'POST',
                        data: new FormData(this),
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            alert(data);
                            $('#cat_produit_form')[0].reset();
                            $('#catProduitModal').modal('hide');
                            dataTable.ajax.reload();
                        }
                    });
                }
                else {
                    alert("Tous les champs sont obligatoires");
                }
            });

            $(document).on('click', '.cat_update', function () {
                var cat_produit_id = $(this).attr("id");
                $.ajax({
                    url: "/assodeal/view/categorie-produit/fetch_single.php",
                    method: "POST",
                    data: {cat_produit_id: cat_produit_id},
                    dataType: "json",
                    success: function (data) {
                        $("#catProduitModal").modal('show');
                        $('#cat-libelle').val(data.libelle);
                        $('.modal-title').text("Modifier");
                        $('#cat_produit_id').val(cat_produit_id);
                        $('#action_cat').val("Edit");
                        $('#operation_cat').val("Edit");
                    }
                })
            });

            $(document).on('click', '.cat_delete', function () {
                var cat_produit_id = $(this).attr("id");
                if (confirm("Etes vous sur de vouloir supprimer ce champ?")) {
                    $.ajax({
                        url: "/assodeal/view/categorie-produit/delete.php",
                        method: "POST",
                        data: {cat_produit_id: cat_produit_id},
                        success: function (data) {
                           alert(data);
                            dataTable.ajax.reload();
                        }
                    });
                }
                else {
                    return false;
                }
            });
        }
    }
    init();
    $(".nav li").click(function () {
        $('#cat_produit_data').DataTable().clear().destroy();
       init();
    });

});
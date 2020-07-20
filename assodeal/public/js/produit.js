$(document).ready(function () {
    function init() {
        if ($('#produit').attr("style") !== "display: none;") {
            $('#add_button_pdt').click(function () {

                if ($('#produit_form')[0]) {
                    $('#produit_form')[0].reset();
                }
                $('.modal-title').text("Ajouter un produit");
                $('#action_pdt').val("Add");
                $('#operation_pdt').val("Add");
                $('#produit_uploaded_image').html('');
            });


            var dataTable = $('#produit_data').DataTable({
                "processing": true,
                "serverSide": true,
                "order": [],
                "ajax": {
                    url: "/assodeal/view/produit/fetch.php",
                    type: "POST"
                },
                "columnDefs": [
                    {
                        "targets": [10, 11],
                        "orderable": false,
                    }
                ]
            });

            $(document).on('submit', '#produit_form', function (event) {
                event.preventDefault();
                var libelle = $('#libelle').val();
                var quantite = $('#quantite').val();
                var prixReel = $('#prixReel').val();
                var prixVente = $('#prixVente').val();
                var detail = $('#detail').val();
                var couleur = $('#couleur').val();
                var poids = $('#poids').val();
                var abreviation = $('#abreviation').val();
                var categorie = $('#selectCategorie').find("option:selected").val();
                var extension = $('#produit_image').val().split('.').pop().toLowerCase();
                if (extension != '') {
                    if (jQuery.inArray(extension, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                        alert("Invalid Image File");
                        $('#produit_image').val('');
                        return false;
                    }
                }

                if (libelle !== '' && quantite !== '' && prixReel !== '' && prixVente !== '' && categorie !== '') {
                    $.ajax({
                        url: "/assodeal/view/produit/insert.php",
                        method: 'POST',
                        data: new FormData(this),
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            alert(data);
                            if (data == "OK") {
                                $('#produit_form')[0].reset();
                                $('#produitModal').modal('hide');
                                dataTable.ajax.reload();
                            }
                        }
                    });
                }
                else {
                    alert("Tous les champs sont obligatoires");
                }
            });

            $(document).on('click', '.pdt_update', function () {
                var produit_id = $(this).attr("id");
                $.ajax({
                    url: "/assodeal/view/produit/fetch_single.php",
                    method: "POST",
                    data: {produit_id: produit_id},
                    dataType: "json",
                    success: function (data) {
                        $("#produitModal").modal('show');
                        $('#libelle').val(data.libelle);
                        $('#quantite').val(data.stock);
                        $('#prixReel').val(data.prixReel);
                        $('#prixVente').val(data.prixVente);
                        $('#detail').val(data.detail);
                        $('#selectCategorie').val(data.idCategorie);
                        $('#couleur').val(data.couleur);
                        $('#poids').val(data.poids);
                        $('#abreviation').val(data.abreviation);
                        $('#produit_uploaded_image').html(data.produit_image);
                        $('.modal-title').text("Modifier");
                        $('#produit_id').val(produit_id);
                        $('#action_pdt').val("Edit");
                        $('#operation_pdt').val("Edit");
                    }
                })
            });

            $(document).on('click', '.pdt_delete', function () {
                var produit_id = $(this).attr("id");
                if (confirm("Etes vous sur de vouloir supprimer ce champ?")) {
                    $.ajax({
                        url: "/assodeal/view/produit/delete.php",
                        method: "POST",
                        data: {produit_id: produit_id},
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
        $('#produit_data').DataTable().clear().destroy();
        init();
    });

});
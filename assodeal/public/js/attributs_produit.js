$(document).ready(function(){

    function init() {
        if ($('#attribut-produit').attr("style") !== "display: none;") {
            $('#add_button_att').click(function () {
                if ($('#att_produit_form')[0]) {
                    $('#att_produit_form')[0].reset();
                }
                $('.modal-title').text("Ajouter un attribut de produit");
                $('#action_att').val("Add");
                $('#operation_att').val("Add");
            });


            var dataTable = $('#att_produit_data').DataTable({
                "processing": true,
                "serverSide": true,
                "order": [],
                "ajax": {
                    url: "/assodeal/view/attribut-produit/fetch.php",
                    type: "POST"
                },
                "columnDefs": [
                    {
                        "targets": [3, 4],
                        "orderable": false,
                    }
                ]
            });

            $(document).on('submit', '#att_produit_form', function (event) {
                event.preventDefault();
                var couleur = $('#attr-couleur').val();
                var poids = $('#attr-poids').val();
                var abreviation = $('#attr-abv').val();
                if (couleur !== '' && poids !== '' && abreviation !== '') {
                    $.ajax({
                        url: "/assodeal/view/attribut-produit/insert.php",
                        method: 'POST',
                        data: new FormData(this),
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            alert(data);
                            $('#att_produit_form')[0].reset();
                            $('#attProduitModal').modal('hide');
                            dataTable.ajax.reload();
                        }
                    });
                }
                else {
                    alert("Tous les champs sont obligatoires");
                }
            });

            $(document).on('click', '.att_update', function () {
                var att_produit_id = $(this).attr("id");
                $.ajax({
                    url: "/assodeal/view/attribut-produit/fetch_single.php",
                    method: "POST",
                    data: {att_produit_id: att_produit_id},
                    dataType: "json",
                    success: function (data) {
                        $("#attProduitModal").modal('show');
                        $('#attr-couleur').val(data.couleur);
                        $('#attr-poids').val(data.poids);
                        $('#attr-abv').val(data.abreviation);
                        $('.modal-title').text("Modifier");
                        $('#att_produit_id').val(att_produit_id);
                        $('#action_att').val("Edit");
                        $('#operation_att').val("Edit");
                    }
                })
            });

            $(document).on('click', '.att_delete', function () {
                var att_produit_id = $(this).attr("id");
                if (confirm("Etes vous sur de vouloir supprimer ce champ?")) {
                    $.ajax({
                        url: "/assodeal/view/attribut-produit/delete.php",
                        method: "POST",
                        data: {att_produit_id: att_produit_id},
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
        $('#att_produit_data').DataTable().clear().destroy();
        init();
    });
});
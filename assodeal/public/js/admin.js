$(function () {
    $("#attribut-produit").hide();
    $("#categorie-produit").hide();
    $("#li-att-produit").click(function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
        $("#categorie-produit").hide();
        $("#attribut-produit").show();
    });
    $("#li-cat-produit").click(function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
        $("#attribut-produit").hide();
        $("#categorie-produit").show();
    });
});
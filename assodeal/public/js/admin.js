$(function () {
    $("#attribut-produit").hide();
    $("#categorie-produit").hide();
    $("#produit").hide();
    $("#li-att-produit").click(function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
        $("#categorie-produit").hide();
        $("#produit").hide();
        $("#attribut-produit").show();
    });
    $("#li-cat-produit").click(function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
        $("#attribut-produit").hide();
        $("#produit").hide();
        $("#categorie-produit").show();
    });
    $("#li-produit").click(function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
        $("#attribut-produit").hide();
        $("#categorie-produit").hide();
        $("#produit").show();
    });
});
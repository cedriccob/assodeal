<?php
include ("../session.php");

require_once('../../model/AttributsProduit.php');

$data = array();
$att = new AttributsProduit();
$result = $att->getAllAttributs();
$filteredRows='';
$resArray=$result;
if(isset($_POST["search"]["value"]) && $_POST["search"]["value"]!="")
{
    $search = $_POST["search"]["value"]; //occurence à rechercher
    $result2 = $att->getSearchAttributsPRoduit($search, $_SESSION['tokenUser']);
    $filteredRows = count($result2);
    $resArray = $result2;
}

if(isset($_POST["order"]))
{   $dir = strtoupper( $_POST['order']['0']['dir']);//direction du tri
    $arrKey = array_keys($resArray[0]); //récupération des noms des colonnes
    $idx = $_POST['order']['0']['column']; //nom de la colonne sur laquelle on veut trier
    $col = array_column($resArray, $arrKey[$idx+1]); //indice+1 car la première colonne correspond à l'id
    array_multisort($col, $dir=="asc"?SORT_ASC:SORT_DESC, $resArray);
}

    foreach ($resArray as $row) {

        $sub_array = array();
        $sub_array[] = $row["couleurProduit"];
        $sub_array[] = $row["poidsProduit"];
        $sub_array[] = $row["abreviationProduit"];
        $sub_array[] = '<button type="button" name="update" id="' . $row["idAttributProduit"] . '" class="btn btn-warning btn-xs att_update">Update</button>';
        $sub_array[] = '<button type="button" name="delete" id="' . $row["idAttributProduit"] . '" class="btn btn-danger btn-xs att_delete">Delete</button>';
        $data[] = $sub_array;
    }

$output = array(
    "draw"    => intval($_POST["draw"]),
    "recordsFiltered" => $filteredRows,
    "recordsTotal"  =>  count($result),
    "data"  => $data
);
echo json_encode($output);
?>
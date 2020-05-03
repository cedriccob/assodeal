<?php
/**
 * Created by IntelliJ IDEA.
 * User: cedri
 * Date: 27/04/2020
 * Time: 23:16
 */

require '../../vendor/autoload.php';

use GuzzleHttp\Client;

class CategorieProduit
{
    private $id;
    private $libelle;

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getLibelle()
    {
        return $this->libelle;
    }

    /**
     * @param mixed $libelle
     */
    public function setLibelle($libelle)
    {
        $this->libelle = $libelle;
    }

    public function getCategorieProduitById($id, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/categorie-produit/']);
        $response = $client->request('GET', $id, ['headers' => $headers]);
        return json_decode($response->getBody(), true);
    }

    public function getAllCategories(){
        $client = new Client();
        $response = $client->get('http://localhost:8080/categorie-produit/all');
        return json_decode($response->getBody(), true);
    }

    public function saveCategorie($categorie, $token){
        $arrayCategorie = [
            "libelleCategorieProduit" => $categorie->getLibelle()
        ];
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new Client();
        $response = $client->request('POST','http://localhost:8080/categorie-produit/add', array('headers'=>$headers, 'json'=>$arrayCategorie));
        return $response->getStatusCode();
    }

    public function deleteCategorie($id, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/categorie-produit/']);
        $response = $client->request('DELETE', $id, ['headers' => $headers]);
        return $response->getStatusCode();
    }


    public function updateCategorie($categorie, $token, $id)
    {
        $arrayCategorie = [
            "libelleCategorieProduit" => $categorie->getLibelle()
        ];

        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/categorie-produit/']);
        $response = $client->request('PUT', $id, array('headers'=>$headers, 'json'=>$arrayCategorie));
        return $response->getStatusCode();
    }

    public function getSearchCategorie($query, $token)
    {
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new Client();
        $response = $client->request('GET', 'http://localhost:8080/categorie-produit/all/search', array('headers'=>$headers, 'query'=>['search'=>$query]));
        return json_decode($response->getBody(), true);
    }


}
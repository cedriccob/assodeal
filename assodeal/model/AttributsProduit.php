<?php
/**
 * Created by IntelliJ IDEA.
 * User: cedri
 * Date: 27/04/2020
 * Time: 23:16
 */


require '../../vendor/autoload.php';

use GuzzleHttp\Client;

class AttributsProduit
{
    private $id;
    private $couleur;
    private $poids;
    private $abreviation;

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
    public function getCouleur()
    {
        return $this->couleur;
    }

    /**
     * @param mixed $couleur
     */
    public function setCouleur($couleur)
    {
        $this->couleur = $couleur;
    }

    /**
     * @return mixed
     */
    public function getPoids()
    {
        return $this->poids;
    }

    /**
     * @param mixed $poids
     */
    public function setPoids($poids)
    {
        $this->poids = $poids;
    }

    /**
     * @return mixed
     */
    public function getAbreviation()
    {
        return $this->abreviation;
    }

    /**
     * @param mixed $abreviation
     */
    public function setAbreviation($abreviation)
    {
        $this->abreviation = $abreviation;
    }



    public function getAttributeById($id, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/attributs-produit/']);
        $response = $client->request('GET', $id, ['headers' => $headers]);
        return json_decode($response->getBody(), true);
    }

    public function getAllAttributs(){
        $client = new Client();
        $response = $client->get('http://localhost:8080/attributs-produit/all');
        return json_decode($response->getBody(), true);
    }

    public function saveAttributs($attribut, $token){
        $arrayAttribut = [
            'couleurProduit' => $attribut->getCouleur(),
            'poidsProduit' => $attribut->getPoids(),
            'abreviationProduit' => $attribut->getAbreviation()
        ];
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new Client();
        $response = $client->request('POST','http://localhost:8080/attributs-produit/add', array('headers'=>$headers, 'json'=>$arrayAttribut));
        return $response->getStatusCode();
    }

    public function deleteAttributs($id, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/attributs-produit/']);
        $response = $client->request('DELETE', $id, ['headers' => $headers]);
        return $response->getStatusCode();
    }


    public function updateAttributs($attribut, $token, $id)
    {
        $arrayAttribut = [
            'couleurProduit' => $attribut->getCouleur(),
            'poidsProduit' => $attribut->getPoids(),
            'abreviationProduit' => $attribut->getAbreviation()
        ];
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/attributs-produit/']);
        $response = $client->request('PUT', $id, array('headers'=>$headers, 'json'=>$arrayAttribut));
        return $response->getStatusCode();
    }

    public function getSearchAttributsPRoduit($query, $token)
    {
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new Client();
        $response = $client->request('GET', 'http://localhost:8080/attributs-produit/all/search', array('headers'=>$headers, 'query'=>['search'=>$query]));
        return json_decode($response->getBody(), true);
    }
}
<?php
/**
 * Created by IntelliJ IDEA.
 * User: cedri
 * Date: 27/04/2020
 * Time: 23:19
 */

require '../../vendor/autoload.php';

use GuzzleHttp\Client;

class Produit
{
    private $id;
    private $libelle;
    private $prixReel;
    private $prixVente;
    private $quantite;
    private $detail;
    private $attribut;
    private $categorie;
    private $couleur;
    private $poids;
    private $abreviation;
    private $image;

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

    /**
     * @return mixed
     */
    public function getPrixReel()
    {
        return $this->prixReel;
    }

    /**
     * @param mixed $prixReel
     */
    public function setPrixReel($prixReel)
    {
        $this->prixReel = $prixReel;
    }

    /**
     * @return mixed
     */
    public function getPrixVente()
    {
        return $this->prixVente;
    }

    /**
     * @param mixed $prixVente
     */
    public function setPrixVente($prixVente)
    {
        $this->prixVente = $prixVente;
    }

    /**
     * @return mixed
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param mixed $quantite
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }

    /**
     * @return mixed
     */
    public function getDetail()
    {
        return $this->detail;
    }

    /**
     * @param mixed $detail
     */
    public function setDetail($detail)
    {
        $this->detail = $detail;
    }

    /**
     * @return mixed
     */
    public function getAttribut()
    {
        return $this->attribut;
    }

    /**
     * @param mixed $attribut
     */
    public function setAttribut($attribut)
    {
        $this->attribut = $attribut;
    }

    /**
     * @return mixed
     */
    public function getCategorie()
    {
        return $this->categorie;
    }

    /**
     * @param mixed $categorie
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;
    }

    /**
     * @return mixed
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param mixed $image
     */
    public function setImage($image)
    {
        $this->image = $image;
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




    public function getProduitById($id, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/produit/']);
        $response = $client->request('GET', $id, ['headers' => $headers]);
        return json_decode($response->getBody(), true);
    }

    public function getAllProduits(){
        $client = new Client();
        $response = $client->get('http://localhost:8080/produit/all');
        return json_decode($response->getBody(), true);
    }

    public function saveProduitMultiPart($produit, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];

        echo "getImage".$produit->getImage();
        $client = new Client();
        $response = $client->request('POST','http://localhost:8080/produit/add', array('headers'=>$headers, 'multipart'=>[
            [
                'name' => 'libelleProduit',
                'contents' => $produit->getLibelle()
            ],
            [
                'name' => 'prixReelProduit',
                'contents' => $produit->getPrixReel()
            ],
            [
                'name' => 'prixVenteProduit',
                'contents' => $produit->getPrixVente()
            ],
            [
                'name' => 'qtStockProduit',
                'contents' => $produit->getQuantite()
            ],
            [
                'name' => 'imageProduit',
                'contents' => $produit->getImage()
            ],
            [
                'name' => 'idCategorieProduit',
                'contents' => $produit->getCategorie()
            ]

        ]));
        return $response->getStatusCode();
    }

    public function saveProduit($produit, $token){
        $arrayProduit = [
            'libelleProduit' => $produit->getLibelle(),
            'qtStockProduit' => $produit->getQuantite(),
            'prixReelProduit' => $produit->getPrixReel(),
            'prixVenteProduit' => $produit->getPrixVente(),
            'categorieProduit' => $produit->getCategorie(),
            'couleurProduit' => $produit->getCouleur(),
            'poidsProduit' => $produit->getPoids(),
            'abreviationProduit' => $produit->getAbreviation(),
            'imageProduit' => $produit->getImage()
        ];
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new Client();
        $response = $client->request('POST','http://localhost:8080/produit/add', array('headers'=>$headers, 'json'=>$arrayProduit));
        return $response->getStatusCode();
    }

    public function deleteProduit($id, $token){
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/produit/']);
        $response = $client->request('DELETE', $id, ['headers' => $headers]);
        return $response->getStatusCode();
    }


    public function updateProduit($produit, $token, $id)
    {
        $arrayProduit = [
            'libelleProduit' => $produit->getLibelle(),
            'qtStockProduit' => $produit->getQuantite(),
            'prixReelProduit' => $produit->getPrixReel(),
            'prixVenteProduit' => $produit->getPrixVente(),
            'attributsProduit' => $produit->getAttribut(),
            'categorieProduit' => $produit->getCategorie(),
            'couleurProduit' => $produit->getCouleur(),
            'poidsProduit' => $produit->getPoids(),
            'abreviationProduit' => $produit->getAbreviation(),
            'imageProduit' => $produit->getImage()
        ];
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/produit/']);
        $response = $client->request('PUT', $id, array('headers'=>$headers, 'json'=>$arrayProduit));
        return $response->getStatusCode();
    }

    public function getSearchProduit($query, $token)
    {
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new Client();
        $response = $client->request('GET', 'http://localhost:8080/produit/all/search', array('headers'=>$headers, 'query'=>['search'=>$query]));
        return json_decode($response->getBody(), true);
    }


}
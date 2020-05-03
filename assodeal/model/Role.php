<?php
/**
 * Created by IntelliJ IDEA.
 * User: cedri
 * Date: 18/04/2020
 * Time: 16:37
 */
require '../vendor/autoload.php';

use GuzzleHttp\Client;
class Role
{
    private $nameRole;
    private $idRole;

    /**
     * @return mixed
     */
    public function getNameRole()
    {
        return $this->nameRole;
    }

    /**
     * @param mixed $nameRole
     */
    public function setNameRole($nameRole)
    {
        $this->nameRole = $nameRole;
    }




    /**
     * @return mixed
     */
    public function getIdRole()
    {
        return $this->idRole;
    }





    /**
     * @param mixed $idRole
     */
    public function setIdRole($idRole)
    {
        $this->idRole = $idRole;
    }

    public function getRolesForRegister(){
        $client = new Client();
        $response = $client->get('http://localhost:8080/roles/allRegister');
        return json_decode($response->getBody(), true);
    }

    public function getRoles(){
        $client = new Client();
        $response = $client->get('http://localhost:8080/roles/all');
        return json_decode($response->getBody(), true);
    }

    public function getRoleById($id){
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/roles/']);
        $response = $client->request('GET', $id);
        return json_decode($response->getBody(), true);
    }
}
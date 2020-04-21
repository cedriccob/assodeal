<?php
/**
 * Created by IntelliJ IDEA.
 * User: cedri
 * Date: 11/04/2020
 * Time: 17:14
 */
require '../vendor/autoload.php';

use GuzzleHttp\Client;

class User
{

    private $id;
    private $nom;
    private $prenom;
    private $mail;
    private $contact;
    private $username;
    private $password;
    private $confirmPassword;
    private $nationalite;
    private $paysResidence;
    private $ville;
    private $adresse;
    private $status;
    private $dateEnregistrement;
    private $dateDernierLogin;
    private $role;
    private $token;

    /**
     * @return mixed
     */
    public function getRole()
    {
        return $this->role;
    }

    /**
     * @param mixed $role
     */
    public function setRole($role)
    {
        $this->role = $role;
    }


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
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param mixed $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return mixed
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param mixed $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @return mixed
     */
    public function getMail()
    {
        return $this->mail;
    }

    /**
     * @param mixed $mail
     */
    public function setMail($mail)
    {
        $this->mail = $mail;
    }

    /**
     * @return mixed
     */
    public function getContact()
    {
        return $this->contact;
    }

    /**
     * @param mixed $contact
     */
    public function setContact($contact)
    {
        $this->contact = $contact;
    }

    /**
     * @return mixed
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * @param mixed $username
     */
    public function setUsername($username)
    {
        $this->username = $username;
    }

    /**
     * @return mixed
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * @param mixed $password
     */
    public function setPassword($password)
    {
        $this->password = $password;
    }

    /**
     * @return mixed
     */
    public function getConfirmPassword()
    {
        return $this->confirmPassword;
    }

    /**
     * @param mixed $confirmPassword
     */
    public function setConfirmPassword($confirmPassword)
    {
        $this->confirmPassword = $confirmPassword;
    }

    /**
     * @return mixed
     */
    public function getNationalite()
    {
        return $this->nationalite;
    }

    /**
     * @param mixed $nationalite
     */
    public function setNationalite($nationalite)
    {
        $this->nationalite = $nationalite;
    }

    /**
     * @return mixed
     */
    public function getPaysResidence()
    {
        return $this->paysResidence;
    }

    /**
     * @param mixed $paysResidence
     */
    public function setPaysResidence($paysResidence)
    {
        $this->paysResidence = $paysResidence;
    }

    /**
     * @return mixed
     */
    public function getVille()
    {
        return $this->ville;
    }

    /**
     * @param mixed $ville
     */
    public function setVille($ville)
    {
        $this->ville = $ville;
    }

    /**
     * @return mixed
     */
    public function getAdresse()
    {
        return $this->adresse;
    }

    /**
     * @param mixed $adresse
     */
    public function setAdresse($adresse)
    {
        $this->adresse = $adresse;
    }

    /**
     * @return mixed
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * @param mixed $status
     */
    public function setStatus($status)
    {
        $this->status = $status;
    }

    /**
     * @return mixed
     */
    public function getDateEnregistrement()
    {
        return $this->dateEnregistrement;
    }

    /**
     * @param mixed $dateEnregistrement
     */
    public function setDateEnregistrement($dateEnregistrement)
    {
        $this->dateEnregistrement = $dateEnregistrement;
    }

    /**
     * @return mixed
     */
    public function getDateDernierLogin()
    {
        return $this->dateDernierLogin;
    }

    /**
     * @param mixed $dateDernierLogin
     */
    public function setDateDernierLogin($dateDernierLogin)
    {
        $this->dateDernierLogin = $dateDernierLogin;
    }


    /**
     * @return mixed
     */
    public function getToken()
    {
        return $this->token;
    }

    /**
     * @param mixed $token
     */
    public function setToken($token)
    {
        $this->token = $token;
    }

    public function register($user)
    {
        $arrayUser = [
            'nom' => $user->getNom(),
            'prenom' => $user->getPrenom(),
            'mail' => $user->getMail(),
            'nationnalite' => $user->getNationalite(),
            'adresse' => $user->getAdresse(),
            'contact' => $user->getContact(),
            'ville' => $user->getVille(),
            'paysResidence' => $user->getPaysResidence(),
            'username' => $user->getUsername(),
            'password' => $user->getPassword(),
            'confirmPassword' => $user->getConfirmPassword(),
            'dateEnregistrement' => date('Y-m-d'),
            'role' => $user->getRole()
        ];
        $client = new Client();
        $response = $client->post('http://localhost:8080/register', [GuzzleHttp\RequestOptions::JSON => $arrayUser]);
        return $response->getStatusCode();
    }

    public function login($user)
    {
        $arrayUser = [
            'username' => $user->getUsername(),
            'password' => $user->getPassword(),
        ];

        $client = new Client();
        $response = $client->post('http://localhost:8080/authenticate', [GuzzleHttp\RequestOptions::JSON => $arrayUser]);
        return json_decode($response->getBody(), true);
    }

    public function getVerificationToken($tokenMail)
    {
        $client = new Client();
        $response = $client->request('POST', 'http://localhost:8080/confirm-account', ['query' => ['token' => $tokenMail]]);
        return $response->getStatusCode();
    }

    public function findUserByLogin($username, $token)
    {
        $headers = [
            'Authorization' => 'Bearer ' . $token
        ];
        $client = new GuzzleHttp\Client(['base_uri' => 'http://localhost:8080/user/']);
        $response = $client->request('GET', $username, ['headers' => $headers]);
        return json_decode($response->getBody(), true);
    }
}
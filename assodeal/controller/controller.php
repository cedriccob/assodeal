<?php

session_start();
require('../model/User.php');
require('../model/Role.php');


 if(isset($_POST['confirmPassword']) && isset($_POST['tel']) && isset($_POST['email']) &&
 isset($_POST['password']) && isset($_POST['prenom']) && isset($_POST['username']) && isset($_POST['nom']) && isset($_POST['selectRole'])) {
     $confirmPassword=$_POST['confirmPassword'];
     $contact=$_POST['tel'];
     $mail=$_POST['email'];
     $nom=$_POST['nom'];
     $password=$_POST['password'];
     $prenom=$_POST['prenom'];
     $username=$_POST['username'];
     $idRole=$_POST['selectRole'];
     $role = new Role();
     $user = new User();
     $roleObject = $role->getRoleById($idRole);
     $user->setConfirmPassword($confirmPassword);
     $user->setContact($contact);
     $user->setMail($mail);
     $user->setNom($nom);
     $user->setPrenom($prenom);
     $user->setUsername($username);
     $user->setPassword($password);
     $user->setRole($roleObject);
     $res = $user->register($user);

     if($res=='200'){
         if (!isset($_SESSION['mail'])) {
             $_SESSION['mail'] =  $user->getMail();
         }
         header("Location: /assodeal/view/successfulRegister.php");
     }
     else{
        echo "Une erreur est survenue lors de l'inscription";
     }
 }
else if(isset($_POST['login']) && isset($_POST['password'])) {
    $login = $_POST['login'];
    $pass = $_POST['password'];
    if ($login && $pass) {
        $user = new User();
        $user->setUsername($login);
        $user->setPassword($pass);
        if (isset($_SESSION['tokenUser'])) {// si on a deja une variable session , c'est qu'on est deja connecté
            header("Location: /assodeal/index.php");//redirection vers la page d'accueil
        } else { // on est pas connecté on va s'authentifier en base
            $token = $user->login($user);
            if (isset($token['token'])) { // on a reçu une réponse et c'est le token
                echo "ok avant user info";
                $userInfo = $user->findUserByLogin($login, $token['token']);
                $_SESSION['tokenUser'] = $token['token'];
                $_SESSION['login'] = $login;
                $_SESSION['role'] = $userInfo['role']['codeRole'];
                header("Location: /assodeal/index.php");
            } else { //pas de réponse token reçue, erreur de connexion
                echo "Erreur de connexion";
                header("Location: /assodeal/view/login.php");
            }
        }
    }
}
else if(isset($_GET['token'])){

        $tokenMail = $_GET['token'];
        $user=new User();
        $res = $user->getVerificationToken($tokenMail);
        if($res=='201'){
            $_SESSION['success'] = "OK";
            header("Location: /assodeal/view/successfulRegister.php");
        }
        else{
            echo 'erreur lors de la vérification du mail';
            unset($_SESSION['nom']);
            unset($_SESSION['prenom']);
            unset($_SESSION['mail']);
            unset($_SESSION['token']);
            unset($_SESSION['success']);
        }
}
else{
    header("Location: /assodeal/view/login.php");
}

<?php include 'header.php';
require('../model/Role.php');
?>

</div>
</div>
</div>
<?php include "main-menu.php" ?>
<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Création d'un compte client</h4>


            <form action="../controller/controller.php" method="post" id="form-register">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Nom</i> </span>
                    </div>
                    <input class="form-control" type="text" name="nom" id="nom"
                           placeHolder="Entrez votre nom de famille">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Prénom</span>
                    </div>
                    <input class="form-control" type="text" name="prenom" id="prenom"
                           placeHolder="Entrez votre prénom">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Téléphone </span>
                    </div>
                    <input class="form-control" type="text" name="tel" id="tel"
                           placeHolder="Entrez votre numéro de téléphone fixe ou mobile">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> E-mail </span>
                    </div>
                    <input class="form-control" type="email" name="email" id="email"
                           placeHolder="Entrez votre adresse mail">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Pseudo </span>
                    </div>
                    <input class="form-control" type="text" name="username" id="username"
                           placeHolder="Entrez un pseudo">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Mot de passe</span>
                    </div>
                    <input class="form-control" type="password" name="password" id="password"
                           placeHolder="Entrez votre mot de passe">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Confirmation du mot de passe</span>
                    </div>
                    <input class="form-control" type="password" name="confirmPassword"
                           id="confirmPassword" placeHolder="Entrez à nouveau votre mot de passe">
                </div>

                <div class="form-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Vous êtes </span>
                    </div>
                    <select class="selectpicker" name="selectRole" id="selectRole">
                        <?php
                        $role = new Role();
                        $result = $role->getRoles();
                        foreach ($result as $key => $value) {
                            echo "<option value = '" . $value["idRole"] . "'>" . $value["nameRole"] . "</option >";
                        }
                        ?>
                    </select>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submit"> Créez votre compte</button>
                </div> <!-- form-group// -->
                <p class="text-center">Déjà inscrit? <a href="login.php">Connectez-vous</a></p>
            </form>
        </article>
    </div>
</div>
<?php include 'footer.php' ?>
<script src="../public/js/register.js"></script>


<?php include 'header.php'?>

    </div>
    </div>
    </div>
<?php include "register-login.php"?>
<?php include 'main-menu.php'?>

<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Connexion</h4>

            <form action="/assodeal/controller/controller.php" method="post" id="form-login">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input class="form-control" type="text" name="login" id="login"
                           placeHolder="Entrez votre pseudo">
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input class="form-control" type="password" name="password" id="password"
                           placeHolder="Entrez votre mot de passe">
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submit"> Connexion</button>
                </div> <!-- form-group// -->
                <p class="text-center">Pas encore inscrit? <a href="register.php">Inscrivez-vous</a> </p>
            </form>
        </article>
    </div>
</div>

<?php

include '../view/footer.php';
?>
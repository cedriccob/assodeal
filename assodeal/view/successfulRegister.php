<?php
include 'header.php';

session_start();

?>

</div>
</div>
</div>
<?php include "main-menu.php"?>
<div class="container">
<?php
if(isset($_SESSION['mail'])){
    echo "<p> Un mail a été envoyé à l'adresse mail : ".$_SESSION['mail']."</p>";
    unset($_SESSION['mail']);
}
else if(isset($_SESSION['success'])){
    echo "Votre e-mail a été vérifié, cliquez <a href='/assodeal/view/login.php'>ici</a>pour vous connecter";
    unset($_SESSION['success']);
}
?>
</div>
<?php include 'footer.php'?>
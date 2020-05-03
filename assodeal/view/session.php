<?php
session_start();
// inactive in seconds
$inactive = 1800;
if( !isset($_SESSION['timeout']) ) {
    $_SESSION['timeout'] = time() + $inactive;
}
$session_life = time() - $_SESSION['timeout'];

if($session_life > $inactive)
{  session_destroy(); header("Location:/assodeal/view/login.php");     }

$_SESSION['timeout']=time();
?>
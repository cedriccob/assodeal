<?php
session_start();
?>
                <div class="user-info pull-right">
                    <div class="user">
                            <ul>
                                <?php if(isset($_SESSION['tokenUser']) && isset($_SESSION['login']) && isset($_SESSION['role'])) {
                                        echo "<li>Bienvenue " . $_SESSION['login']."</li>";
                                        echo "<li><a href='/assodeal/view/logout.php'>Se déconnecter</a></li>";
                                        if($_SESSION['role']==="adm"){
                                            echo "<li><a href='/assodeal/view/admin.php'>Admin</a></li>";
                                        }
                                    }
                                    else{
                                       echo "<li><a href='/assodeal/view/login.php'>Se connecter</a></li><li><a href='/assodeal/view/register.php'>S'inscrire</a></li>";
                                    }
                                    ?>
                        </ul>
                    </div>
                </div>
                </div>
                </div>
                </div>
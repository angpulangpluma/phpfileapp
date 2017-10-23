<?php
session_start();
session_destroy();
echo 'You have successfully logged out. You will be redirected to the logout page in a few moments.';
header("refresh: 5; URL=login.php");
?>
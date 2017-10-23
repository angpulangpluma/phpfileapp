<?php
   include('config_db.php');
   session_start();
   
   $user_check = $_SESSION['login_user'];
   
   $ses_sql = mysqli_query($db,"select name from users where name = '$user_check' ");
   //var_dump($ses_sql);
   //var_dump($db);
   $row = mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);
   
   $login_session = $row['name'];
   
   if(!isset($_SESSION['login_user'])){
      header("location:login.html");
   }
?>
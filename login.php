<?php
   include("config_db.php");
   session_start();
   
   $error = "";
   $result = "";
   
   if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
	}
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
	  // username and password sent from form 
	  
	  //$myusername = mysql_real_escape_string($db,$_POST['username']);
	  //$mypassword = mysql_real_escape_string($db,$_POST['password']); 
	  
	  $myusername = $_POST['username'];
	  $mypassword = $_POST['password'];
	  
	  mysqli_select_db($db, DB_DATABASE);
	  $sql = "SELECT * FROM users WHERE name = '$myusername' and password = '$mypassword';";
	  //$result = mysqli_query($db,$sql);
	  $result = mysqli_query($db, $sql);
	  //var_dump($result);
	  //var_dump($db);
	  $row = mysqli_fetch_assoc($result);
	  //$active = $row['active'];
	  
	  // If result matched $myusername and $mypassword, table row must be 1 row
		
	  if(mysqli_num_rows($result)) {
		 $_SESSION['login_user'] = $myusername;
		 
		 header("Location: test.php");
		 exit();
	  }else {
	  //put redirect to error_login.html
		$error = "Your Login Name or Password is invalid";
		//echo $result;
		//echo " " . $myusername;
		//echo " " . $mypassword;
	  }
   }
?>
<html>
   
   <head>
      <title>Login Page</title>
      
      <style type = "text/css">
         body {
            font-family:Arial, Helvetica, sans-serif;
            font-size:14px;
         }
         
         label {
            font-weight:bold;
            width:100px;
            font-size:14px;
         }
         
         .box {
            border:#666666 solid 1px;
         }
      </style>
      
   </head>
   
   <body bgcolor = "#FFFFFF">
	
      <div align = "center">
         <div style = "width:300px; border: solid 1px #333333; " align = "left">
            <div style = "background-color:#333333; color:#FFFFFF; padding:3px;"><b>Login</b></div>
				
            <div style = "margin:30px">
               
               <form action = "" method = "post">
                  <label>UserName  :</label><input type = "text" name = "username" class = "box"/><br /><br />
                  <label>Password  :</label><input type = "password" name = "password" class = "box" /><br/><br />
                  <input type = "submit" value = " Submit "/><br />
               </form>
               
               <div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php echo $error; ?></div>
					
            </div>
				
         </div>
			
      </div>

   </body>
</html>
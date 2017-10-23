<?php
	define("DB_SERVER", "localhost");
	define("DB_USERNAME", "root");
	define("DB_PASSWORD", "");
	define("DB_DATABASE", "watereddown");
	$db = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_DATABASE);
	if ($db==false){
		echo "Nope. Not connected.";
	} else echo "Yay! Connected";
?>
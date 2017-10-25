<?php
$secret = 'qvzh65h7ug07rhi';
$token ='I9h5pIu_C2AAAAAAAAAAX-7E5iZmUqhT3n7Jo8MQ7M3c2fyzV6tyMQsdmecbwAoO';
$id = 'DrpBxWithEncryption';

include("session.php");

require_once 'vendor\autoload.php';

use Kunnu\Dropbox\Dropbox;
use Kunnu\Dropbox\DropboxApp;
use Kunnu\Dropbox\DropboxFile;
use Kunnu\Dropbox\Exceptions\DropboxClientException;

$app = new DropboxApp($id, $secret, $token);

$dropbox = new Dropbox($app);

if($_SERVER["REQUEST_METHOD"] === "POST") {
	//var_dump($_FILES['userFile']);
	//var_dump($_POST);
	//var_dump($_FILES);
	//
	$file = $_FILES['userFile'];
	
	$fileName = $file['name'];
	$filePath = $file['tmp_name'];
	
	try{
	
	$dropboxFile = new DropboxFile($filePath);
	$uploadedFile = $dropbox->upload($dropboxFile, "/".$fileName, ['autorename'=> true]);
	
	//echo $uploadedFile->getPathDisplay();
	//var_dump($uploadedFile);
	
	} catch (DropboxClientException $e){
		echo $e->getMessage();
	}
	
	header("location:test.php");
} else echo "Oh no!";

?>

<html>

<head>
<!--<script type="text/javascript">
function getfile(test){
	var source=test.value;
	document.getElementById("selFile").value = source;
	//alert(source);
}
</script>-->
</head>

<body>

<form method="POST" name="uploadform" enctype="multipart/form-data">
<input type='file' name='userFile' id="userFile"><br>
<!--<input type="hidden" id="selFile" name="selFile" value="">-->
<input type='submit' name="upload_btn" value="upload">
</form>

<a href="test.php">Click here to return to home</a>

</body>

</html>
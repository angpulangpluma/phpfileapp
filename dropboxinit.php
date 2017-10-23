<?php
$secret = 'qvzh65h7ug07rhi';
$token ='I9h5pIu_C2AAAAAAAAAAX-7E5iZmUqhT3n7Jo8MQ7M3c2fyzV6tyMQsdmecbwAoO';
$id = 'DrpBxWithEncryption';

require_once 'vendor\autoload.php';

use Kunnu\Dropbox\Dropbox;
use Kunnu\Dropbox\DropboxApp;

$app = new DropboxApp($id, $secret, $token);

$dropbox = new Dropbox($app);

$account = $dropbox->getCurrentAccount();

//Id
$acntid = $account->getAccountId();

//Name
$acntnme = $account->getDisplayName();

//Email
$acntmail = $account->getEmail();

//Account Type
$acnttype = $account->getAccountType();

echo $acntid . " " . $acntnme . " " . $acntmail . " " . $acnttype;

//echo "in app:";
//var_dump($app);
//echo "\n in dropbox:";
//var_dump($dropbox);
//echo "\n";
?>
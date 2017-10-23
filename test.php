<?php
include("session.php");
//shell_exec('"C:\Program Files (x86)\Java\jdk1.8.0_144\bin\javac.exe" greetings.java 2>&1');
//$OUTPUT = shell_exec('"C:\Program Files (x86)\Java\jdk1.8.0_144\bin\java.exe" -cp . greetings 2>&1');
//echo $OUTPUT;

include_once('backgroundprocess.php');
include_once('factory.php');

if (!class_exists(BackgroundProcess))
  echo "Oh no";
else{
$process = new BackgroundProcess('sleep 5');
$process->run();

echo sprintf('Crunching numbers in process %d', $process->getPid());
while ($process->isRunning()) {
    echo '.';
    sleep(1);
}
echo "\nDone.\n";
}

?>

<html>

<head>
<title>Home</title>
</head>

<body>
<a href="logout.php">Click here to logout</a>
</body>

</html>
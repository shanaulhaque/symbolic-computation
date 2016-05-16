<?php
$eqn = $_POST['equation'];
$myfile = fopen("Equation.txt", "w") or die("Unable to open file!");
fwrite($myfile, $eqn);
exec("java -jar SCserver.jar", $output);
echo $output[0];
?>
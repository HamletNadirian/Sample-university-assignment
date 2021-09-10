<?php
$key;
// getting the value of name field
$name = $_POST["name"];
// getting the value of the email field
$email = $_POST["email"];
$age = $_POST["age"];
$time = $_POST["time"];
echo "Hi, ". $name . "<br>";
echo "Your email address: ". $email ."<br>";
echo "Your age:". $age ."<br>";
echo "Time:". $time ."<br>";
if($name==="Hamlet"){

$key=hash_hmac('ripemd160', $name, 'secret');
echo $key;

}
else {
echo "Yo are not Hamlet!" ."<br>";
}
?>





<form action="http://localhost/lab7/index.php" method="post">
Key: <input type="text" name="key2"><br>
<input type="submit">
</form>


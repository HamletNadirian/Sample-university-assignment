<?php
require '../PHPMailerAutoload.php';


$mail=new PHPMailer;
$mail->isSMTP();

$mail->Host = 'smtp.gmail.com';

//Set the SMTP port number - 587 for authenticated TLS, a.k.a. RFC4409 SMTP submission
$mail->Port = 587;

//Set the encryption system to use - ssl (deprecated) or tls
$mail->SMTPSecure = 'tls';

//Whether to use SMTP authentication
$mail->SMTPAuth = true;

//Username to use for SMTP authentication - use full email address for gmail
$mail->Username = "hamlet.nadirian@gmail.com";

//Password to use for SMTP authentication
$mail->Password = "qwerty";

//Set who the message is to be sent from
$mail->From = 'hamlet.nadirian@gmail.com';
$mail->FromName='Hamlet';

$mail->addAddress('gamlet.ossmanov@gmail.com','Hamlet');
$mail->addCC('gamlet.osmanov@gmail.com');

$mail->isHTML(true);
$mail->Subject="Lab 13";
$mail->Body='<p><b>Hello,Hamlet!</b></p>';
$mail->AltBody = 'Hello,Hamlet!';
$mail->addAttachment('fut.png','Wallper - 1');
$mail->addAttachment('mag.jpg','Wallper - 2 ');

if ($mail->send()) {
	echo "Письмо отправлено.";
}else{
	echo "Письмо не отправлено.";
	echo 'Ошибка: '.$mail->ErrorInfo;
}
?>
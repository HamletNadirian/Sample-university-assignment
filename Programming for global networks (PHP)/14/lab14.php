<?php 

define('WIDTH', 300);
define('HEIGHT', 250);

//Какие-то данные
$data = array("OTP"=>(array("C.A"=>60,"exam"=>40)),"SI"=>(array("C.A"=>22,"exam"=>78)),"II"=>(array("C.A"=>22,"exam"=>78)),
        "MITS"=>(array("C.A"=>20,"Practical"=>80)),"IITS"=>(array("exam"=>30,"Practical"=>70)));

$img = imagecreatetruecolor(WIDTH, HEIGHT); //создание изображения
$bg_color = imagecolorallocate($img, 255, 255, 255); // white
$text_color = imagecolorallocate($img, 0, 0, 0); // black
$graphic_color = imagecolorallocate($img, 32, 32, 32); //
$exam_color =imagecolorallocate($img, (int)rand(1,50), (int)rand(1,91), (int)rand(1,109)); //random color
$test_color = imagecolorallocate($img,1, 1, 69);
$score_color = imagecolorallocate($img,231, 231, 231); 

//Рисование закрашенного прямоугольника и линии
imagefilledrectangle($img, 0, 0, WIDTH, HEIGHT, $bg_color);  
imageline($img,(count($data)*50),HEIGHT-18,30,HEIGHT-18,$text_color);//X
imageline($img, 30, 18, 30, HEIGHT-20, $text_color);//Y
//текст
$r=1;
foreach ($data as $course=>$result):
$rectx1 = 35*$r;
$form=1;
$start =0 ;
$stop=0;
foreach ($result as $test => $score):
$exam_color =imagecolorallocate($img, (int)rand(1,50), (int)rand(1,91), (int)rand(1,109));
$strx =36*$r;
$strpt = 0.9;
$start = ($start==0)?HEIGHT-20:$stop;
$stop =($stop==0)? HEIGHT-20-(($score)*2): $start-($score*2);//отступ гистограм по высоте
 imagefilledrectangle($img, $rectx1, $stop, $rectx1+20, $start, $exam_color);//exam ширине []
 imagestringup($img, $strpt, $strx, $start-2, $score, $score_color);//надписи на гистограме по оси Y
 $start++;
 endforeach;
  imagestring($img,0.4,36*$r,HEIGHT-15,$course,$text_color);//надписи на гистограме по оси X
  $r++;
 endforeach;
imagestringup($img,10, 3, 150,"PASS",$text_color); //название шкалы

 for ($i=0;$i<11;$i++){
 imagestringup($img,0.2, 20, 230-($i*20),$i*10,$text_color);//разделения по оси У
 }


header("Content-type: image/png");
imagepng($img);
imagedestroy($img);
?>
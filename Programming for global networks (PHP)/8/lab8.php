<?php

$num=1;
$even = "";
$notEven = "";
$fd = fopen("2.txt", 'w') or die("не удалось создать файл");
$fd2 = fopen("1.txt", 'w') or die("не удалось создать файл");
$fp = fopen("counter.txt", "r") or die("не удалось открыть файл");; // Открываем файл в режиме чтения
if ($fp)
{
while (!feof($fp))
{

echo "$ctr <br/>";
$mytext = fgets($fp, 999);
if(($num%2)==0)
{
$even=$mytext;
echo $even ." четная <br />";

fwrite($fd,$even);

}

else{
$notEven = $mytext;
echo $notEven ." не четная <br />";
fwrite($fd2,$notEven);
}



$num++;
}

}

else echo "Ошибка при открытии файла";




fclose($fp);
fclose($fd);
fclose($fd2);
?>
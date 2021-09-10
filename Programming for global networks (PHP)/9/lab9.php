<!DOCTYPE html>
<html>
<head><title>Lab9</title><meta charset="utf-8"></head>
<body
<span><p>Надірян Г.О. Лабораторна робота № 9</p></span>
<?php
echo date(DATE_RFC822)."<br/>";
// Определение функции для вывода файлов из каталога
$str="";
$counter=0;
function outputFiles($path){
// Проверка существования каталога
if(file_exists($path) && is_dir($path)){
// Поиск файлов в каталоге
$files = glob($path ."/*");
if(count($files) > 0){
// Циклический обход возвращённого массива
foreach($files as $file){
if(is_file("$file")){
// Отображаем только имя файла
//echo basename($file) . "<br>";
$str=basename($file) . "<br>";
} else if(is_dir("$file")){
// Рекурсивно вызываем функцию, если каталоги найдены
outputFiles("$file");
}
}
} 
} else {
echo "ERROR: The directory does not exist.";
}
$pattern = "\.(php)";
if(preg_match("/\b(\.php)\b/", $str, $output_array)){
$GLOBALS['counter']+=1;
}
}

outputFiles("C:\WebServers\home");
echo "Файла формата .php $counter";
?>
</body>
</html>
<?php 
include ("lib.inc");
$n = rand(1,26);
$str = random_string($n);
$str2=search($str);
print"Generation string: $str <br/>";
if(iconv_strlen ($str2)==0){
print"Empty";

}
else{
print"$str2 <br/>";
$n = iconv_strlen ($str2)-1;
$lastSymb = str_split($str2);
print"$lastSymb[$n] <b/>";
}
?>

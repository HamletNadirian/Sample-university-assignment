<?php 
function random_string( $len = 8)
    {
        $pool = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
       return substr(str_shuffle(str_repeat($pool, ceil($len / strlen($pool)))), 0, $len);
        
    }


 function search($str){
    $result = str_split($str);
    $str2="";
for($i=0;$i<count($result);$i++){
if(!ctype_alpha($result[$i]))
{
    $str2.=$result[$i];
} 
 }
    return $str2; 
 }

?>
<?php
$n = rand(0,20);

$dynamicarray = array();

for($i=0;$i<$n;$i++)
{	

    $dynamicarray[$i]=$i;
	$avg+=$dynamicarray[$i];
	print_r ("$dynamicarray[$i] ");
	
}
print_r($dynamicarray);
$avg=$avg/count($dynamicarray);
print_r("<br>");
print_r("Avg = $avg");
print_r("<br>");

for($i=0;$i<$n;$i++)
{	
	if($dynamicarray[$i]<$avg)
		print_r("index[$i]=+");
	
	else
		print_r("index[$i]=-");
	
	print_r("<br>");

}
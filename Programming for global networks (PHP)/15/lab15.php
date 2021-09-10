<html>
   <head>      
      <body>         
	  <style>
body {
  font: normal medium/1.4 sans-serif;
}
table {
  border-collapse: collapse;
  width: 100%;
}
th, td {
  padding: 0.25rem;
  text-align: center;
  border: 1px dot #ccc;
}
tbody tr:nth-child(odd) {
  background: #eee;
}





</style>
<main>
  <span><p>Nadirian Hamlet. CIT-M120D. Laboratory work 15.Bingooooooooooooooooooooooo!</p></span>
</main>
<div class="pyro">
  <div class="before"></div>
  <div class="after"></div>
</div>

         <?php
            $doc = new DOMDocument;
            $xml = simplexml_load_file("Data.xml");
			   
            //file to SimpleXMLElement 
            $simpleXmlElement = simplexml_import_dom($xml);
echo '<table border="1" cellspacing="1" cellpadding="1" bgcolor="#ffcc00"> 
      <tr> 
          <td> <font face="Arial">Group</font> </td> 
          <td> <font face="Arial">Cours</font> </td> 
          <td> <font face="Arial">Name</font> </td> 
          <td> <font face="Arial">Score</font> </td> 
     
      </tr>';
            //Adding the child node
            $child = $xml->addChild('department');
            $child->addChild('group', 'OpenCV');
            $child->addChild('cours', '230');
            $child->addChild('flp', 'Maruthi');
            $child->addChild('score', '5.5');
            $xml->asXML("output.xml");

      
            foreach($xml->children() as $tut) {
		$field1name = $tut->group;
        $field2name = $tut->cours; 
        $field3name = $tut->flp;
        $field4name = $tut->score;
		echo '<tr> 
                  <td>'.$field1name.'</td> 
                  <td>'.$field2name.'</td> 
                  <td>'.$field3name.'</td> 
                  <td>'.$field4name.'</td> 
              </tr>';

            }

         ?>
      </body>
   </head>
</html>
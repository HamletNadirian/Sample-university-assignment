<!DOCTYPE html>
<html>
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
  <span><p>Nadirian Hamlet. CIT-M120D. Laboratory work 12</p></span>
</main>
<?php

function creatDb(){
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];

$conn = new mysqli($servername, $username, $password);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
// Creating a database named newDB
$sql = "CREATE DATABASE $dbName";
if ($conn->query($sql) === TRUE) {
    echo "Database created successfully with the name $dbName <br>";
} else {
    echo "Error creating database: " . $conn->error;
}

// closing connection
$conn->close();
}

function createTableOne(){
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];

	// Create connection
$conn = new mysqli($servername, $username,$password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// sql to create table
$sql = "CREATE TABLE $tableOne (

  `id` int(11) NOT NULL AUTO_INCREMENT,
  `surname` char(30) COLLATE ascii_bin NOT NULL,
  `lastname` char(30) COLLATE ascii_bin NOT NULL,
  `patronym` char(50) COLLATE ascii_bin NOT NULL,
  `record_book_id` int(11) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `learning_rom` enum('day','distance') COLLATE ascii_bin DEFAULT NULL,
  `specialty` char(50) COLLATE ascii_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=ascii COLLATE=ascii_bin AUTO_INCREMENT=9"
;

if ($conn->query($sql) === TRUE) {
  echo "Table $tableOne created successfully <br>";
} else {
  echo "Error creating table: " . $conn->error;
}

}
function createTableTwo(){
	
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];

	// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// sql to create table
$sql = "CREATE TABLE $tableTwo (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` char(50) COLLATE ascii_bin NOT NULL,
  `specialty` char(50) COLLATE ascii_bin NOT NULL,
  `number` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `boos_group` char(30) COLLATE ascii_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=ascii COLLATE=ascii_bin AUTO_INCREMENT=7"
;

if ($conn->query($sql) === TRUE) {
  echo "Table $tableTwo created successfully <br>";
} else {
  echo "Error creating table: " . $conn->error;
}

}
function insertDatatoTablOne(){	
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];
// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO $tableTwo (id, department, specialty, number, group_id, boos_group)
VALUES (1, 'OTP', 'SKS', 3, 123, 'Nadirian H O');";

$sql .= "INSERT INTO $tableTwo (id, department, specialty, number, group_id, boos_group)
VALUES (2, 'II', 'KSM', 3, 122, 'Nadirian H O');";

$sql .= "INSERT INTO  $tableTwo (id, department, specialty, number, group_id, boos_group)
VALUES (3, 'PI', 'SP', 2, 121, 'Spisivcev Y A')";

if ($conn->multi_query($sql) === TRUE) {
  echo "New records created successfully <br>";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
}

function insertDatatoTableTwo(){	
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableTwo = $_POST["tableTwo"];
$tableOne = $_POST["tableOne"];
	
// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (1, 'Hamlet', 'H', 'O', 1234, '1998-06-30', 'day', 'SKS');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (2, 'Hala', 'H', 'O', 2234, '2008-01-21', 'day', 'SP');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (3, 'Yaroslav', 'S', 'A', 4213, '1999-06-12', 'day', 'KSM');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (4, 'Hamlet', 'H', 'O', 1234, '1998-06-30', 'distance', 'SKS');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (5, 'Hala', 'H', 'O', 2234, '2008-01-21', 'distance', 'SP');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (6, 'Hamlet', 'H', 'O', 1234, '1998-06-30', 'distance', 'KSM');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (7, 'Hala', 'H', 'O', 2234, '2008-01-21', 'distance', 'KSM');";

$sql .= "INSERT INTO $tableOne (id, surname, lastname, patronym, record_book_id, birth_date, learning_rom, specialty)
VALUES (8, 'Yaroslav', 'S', 'A', 4213, '1999-06-12', 'distance', 'SKS');";




if ($conn->multi_query($sql) === TRUE) {
  echo "New records created successfully <br>";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
}
function number_student(){
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];
$kol_stud=array();

// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT specialty, COUNT(*) as count FROM $tableOne GROUP BY specialty";
$result = $conn->query($sql);

$i=0;
if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
	 $i++;  
	   $kol_stud[$i] = $row["count"] ;
  // echo "count: " . $row["count"]. "<br>";
  }
} else {
  echo "0 results";
}

//echo $kol[1];
//echo $kol[2];
//echo $kol[3];
return $kol_stud;
}

function name_dep(){
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];
$name_dep=array();

// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT $tableTwo.department from $tableTwo";
$result = $conn->query($sql);

$i=0;
if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
	 $i++;  
	   $name_dep[$i] = $row["department"] ;
 //  echo "count: " . $row["department"]. "<br>";
  }
} else {
  echo "0 results";
}

//echo $kol[1];
//echo $kol[2];
//echo $kol[3];
return $name_dep;
}


function one_ex(){
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];

// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT id, department, specialty, number, group_id, boos_group FROM $tableTwo";
$result = $conn->query($sql);
echo '<table border="1" cellspacing="1" cellpadding="1" bgcolor="#ffcc00"> 
      <tr> 
       
          <td> <font face="Arial">Boss group</font> </td> 
      </tr>';
if ($result->num_rows > 0) {

  while($row = $result->fetch_assoc()) {
   
      
        $field6name = $row["boos_group"]; 

        echo '<tr> 
                
		  <td>'.$field6name.'</td> 
              </tr>';
  }
} else {
  echo "0 results";
}
echo "<br/>";
$conn->close();
}

function  number_dist(){

$kol_dist=array();
$servername = "localhost";
$username = "root";
$password = "";
$dbName = $_POST["dbName"];
$tableOne = $_POST["tableOne"];
$tableTwo = $_POST["tableTwo"];
// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT specialty, COUNT(specialty) as count FROM $tableOne WHERE learning_rom = 'distance' GROUP BY specialty HAVING COUNT(specialty) "; 
$result = $conn->query($sql);
$i=0;
if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
	 $i++;  
	    $kol_dist[$i] = $row["count"];
   //echo "count: " . $row["count"]. "<br>";
  }
} else {
  echo "0 results";
}
return $kol_dist;
}


  creatDb();
  createTableOne();
  createTableTwo();
  insertDatatoTablOne();
  insertDatatoTableTwo();
  one_ex();
	 $count_stud = number_student();
	 $count_dist = number_dist();
	 $name_dep=name_dep();


 echo '<table border="1" cellspacing="1" cellpadding="1" bgcolor="#ffcc00"> 
       <tr> 
           <td> <font face="Arial">Каферда</font> </td> 
          <td> <font face="Arial">Количество студентов</font> </td> 
         <td> <font face="Arial">Количество заочников</font> </td> 

       </tr>';
	   for($i=0;$i<3;$i++){
				 echo '<tr> 
                   <td>'.$name_dep[$i].'</td> 
                   <td>'.$count_stud[$i].'</td> 
                   <td>'.$count_dist[$i].'</td> 
                  
               </tr>';
	   }





?>

</body>
</html>
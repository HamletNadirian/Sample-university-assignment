<!DOCTYPE html>
<html>
<body>
<style>
	* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

html,
body {
  font-family: 'Fira Code', monospace;
  width: 100%;
  height: 100%;
  font-size: 12px;
}

main {
  display: flex;
  height: 10%;
  align-items: center;
  justify-content: center;
}

p {
  cursor: pointer;
  position: relative;
  display: inline-block;
  font-size: 3rem;
  background: linear-gradient(to bottom, #000, #000 60%, #fff 60%, #fff 100%);
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
  background-repeat: no-repeat;
  transition: background 0.2s ease-out;
  white-space: nowrap;
}

span {
  position: relative;
}

span:before {
  content: "";
  position: absolute;
  left: 0;
  right: 0;
  height: 12px;
  background: #000;
  bottom: 9px;
  transition: all 0.2s ease-out;
}

p:hover {
  background-position: 0 11px;
}

span:hover:before {
  transform: translateY(10px)
}
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
<main>
  <span><p>Nadirian Hamlet. CIT-M120D. Laboratory work 4></p></span>
</main>
<?php
interface PrintItself {
	static function table_cols();
	static function table_heading();
	function table_data();

}
interface PrintPC {
static function table_headers();
	function table_data();

}
function td($x) {
	return "<td>".$x."</td>";
}
function th($x) {
	return "<th>".$x."</th>";
}
function th_rowspan($x,$span) {
	return "<th rowspan='".$span."'>".$x."</th>";
}
function th_colspan($x,$span) {
	return "<th colspan='".$span."'>".$x."</th>";
}

class MotherBoard implements PrintItself {
	private $socket;
	private $chipset;
	private $ram;
	private $cpu;
	public function getSocket(){return $this->$socket;}
	public function getChipset(){return $this->$chipset;}
	public function getRam(){return $this->$ram;}
	public function getCpu(){return $this->$cpu;}
	public function setSocket($socket){$this->socket=$socket;}
	public function setChipset($chipset){$this->chipset=$chipset;}
	public function setRam($ram){$this->ram=$ram;}
	public function setCpu($cpu){$this->cpu=$cpu;}

	public function __construct($socket,$chipset,$ram,$cpu){
		$this->socket=$socket;
		$this->chipset=$chipset;
		$this->ram=$ram;
		$this->cpu=$cpu;
	}

	public static function table_cols() {
		return 4;
	}
	public static function table_heading() {
		return th("Socket").th("Chipset").th("RAM").th("CPU");
	}
	public function table_data() {
		return td($this->socket).td($this->chipset).td($this->ram).td($this->cpu);
	}


}
class externalHardDrive implements PrintItself {
private $interface;
private $storageGB;
	public function getInterface(){return $this->$interface;}
	public function getStorageGB(){return $this->$storageGB;}
	public function setInterface($interface){$this->interface=$interface;}
	public function setStorageGB($storageGB){$this->storageGB=$storageGB;}
	public function  __construct($interface,$storageGB){
		$this->interface=$interface;
		$this->storageGB=$storageGB;
	}
	public static function table_cols() {
		return 2;
	}
	public static function table_heading() {
		return th("Interface").th("Socket");
	}
	public function table_data() {
		return td($this->interface).td($this->storageGB."GB");
	}


}

class PowerSupply implements PrintItself {
	private $power;
	private $type;
	public function getPower(){return $this->$power;}
	public function getStorageGB(){return $this->$type;}
	public function setPower($power){$this->power=$power;}
	public function setType($type){$this->type=$type;}
	public function __construct($power,$type){
	$this->power=$power;
	$this->type=$type;
	}
		public static function table_cols() {
		return 2;
	}
	public static function table_heading() {
		return th("Power").th("Type");
	}
	public function table_data() {
		return td($this->power).td($this->type);
	}

}

class PersonalComputer implements PrintPC{
	private $picker;
	private $model;
	private $type;
	private $inputPower;
	private $powerConsumption;
	private $motherboard;
	private $HDD;
	public function __construct($picker,$model,$type,$motherboard,$HDD,$powerConsumption){
		$this->picker=$picker;
		$this->model=$model;
		$this->type=$type;
		$this->motherboard=$motherboard ;
		$this->HDD=$HDD;
		$this->powerConsumption=$powerConsumption ;

	}

	public function getPicker(){
		return $this->picker;
	}
	static function table_headers() {
		return "<tr>".th_rowspan("Picker",2).th_rowspan("Model",2).th_rowspan("Type",2)
			.th_colspan("Motherboard",MotherBoard::table_cols()).th_colspan("External Hard Drive",externalHardDrive::table_cols())
			.th_colspan("Power Supply",PowerSupply::table_cols())."</tr><tr>"
			.MotherBoard::table_heading().externalHardDrive::table_heading().PowerSupply::table_heading()."</tr>";
	}
	

	function table_data() {
		return td($this->picker).td($this->model).td($this->type).$this->motherboard->table_data()
			.$this->HDD->table_data().$this->powerConsumption->table_data();
	}


}

function printTable($classname,$tab) {
	echo("<table border='1'>".call_user_func(array($classname,"table_headers")));
	foreach($tab as $v) {
		echo("<tr>".$v->table_data()."</tr>");
	}
	echo("</table>");
}

$pc = array(
	new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	), 
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),	new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	), 
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),	new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	), 
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),	new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	),
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	), 
		new PersonalComputer("Expert PC","Basic","Mini-tower",new MotherBoard("s-1200","Intel H410","8 GB","Core i3-10400F"),new externalHardDrive("SATA-3","512 GB"),new PowerSupply("500 BT","ATX")
	),new PersonalComputer("Expert PC","Ultimate","Miditower",new MotherBoard("s-1200","Intel H410","16 GB","Core i5-10400F"),
		new externalHardDrive("SATA-3","1024 GB"),new PowerSupply("550 BT","ATX")
	),
	new PersonalComputer("Qube","QB","Miditower",new MotherBoard("s-1200","Intel H410","24 GB","Core i7-10400F"),
		new externalHardDrive("SATA-3","2048 GB"),new PowerSupply("600 BT","ATX")
	), 
	new PersonalComputer("Qbox","i3870","Miditower",new MotherBoard("s-1200","Intel Z390","64 GB","Core i9-9900K"),
		new externalHardDrive("SATA-3","4TB+960GB"),new PowerSupply("1000 BT","ATX")
	)
	);
function mysort($a, $b){
	return strcmp($a->getPicker(),$b->getPicker());
}
usort($pc, mysort);
printTable("PersonalComputer",$pc);

?>
 
</body>
</html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
<div style="width:700px; margin:0 auto; ">
<div>
<h3 style="color:black" ><strong>Welcome to my Generator API !</strong></h3>   
<form  method="POST">
<label style="color:black">Enter KeyName:</label><br />

<input type="text" name="keyName" placeholder="Enter the KeyName"/>
<input type="submit" name="delete" value="Delete the key"/>
<input type="submit" name="add" value="Generate key"/>
<input type="submit" name="show" value="Show keys"/>

<hr>
<table >
<tr  >

<th >keyName </th>
<th >keyValue</th>
<th >keyDate</th>
</tr>
</form>    

<?php

class RestAPI{

  public $servername = "localhost";
  public $username = "root";
  public $password = "";
  public $dbname = "keysgenerator";
  public $conn;
  public $tablename="keygenerated";
  public $keysArray;


  function connect()
  {  //create conn
      $this->conn = new mysqli($this->servername, $this->username, $this->password, $this->dbname);
     // Check connection
      if ($this->conn->connect_error) {
          die("Connection failed: " . $this->conn->connect_error);
      }
     
  }

  
 function showKeys(){
  //connect to the db
      $this->connect();
  //request
  $sql = "SELECT * FROM keygenerated";
  $result = $this->conn->query($sql);

  if ($result->num_rows > 0) {
      // output data of each row
      while($row = $result->fetch_assoc()) {
       
          $post_item=array(
              'keyValue'=> $row["keyValue"],
              'keyName'=>$row["keyName"],
              'dateTime'=> $row["dateTime"]
          );
        
          $keyname=$row['keyName'];
          $keyval=$row['keyValue'];
          $keydate=$row['dateTime'];
         
          { echo "<tr>";
            echo "<td>" . $keyname ."</td>";
            echo "<td>" . $keyval. "</td>";
            echo "<td>" .$keydate . "</td>";
            echo "</tr>";

           
           
          }
   
        
          }
  } else {
      echo "0 results";
  }
  
  $this->conn->close();
 } 

 function deleteKey($key_name)
 {
  $this->connect();
  $sql1 = "DELETE FROM keygenerated WHERE keyName='".$key_name."'";
  $this->conn->query($sql1);
  $this->conn->close();
 }

 function addKey($key_name)
 {    
       $this->connect();
       $newkey= $this->generateKey();
       $sql1 = "INSERT INTO keygenerated (keyName,keyValue,dateTime) VALUES ('".$key_name."','".$newkey."','".date("Y-m-d H:i:s")."')";
       $this->conn->query($sql1);
       $this->conn->close();
 }

 function generateKey(){
  //générer une clé
  $permitted_chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
  $key=substr(str_shuffle($permitted_chars), 0, 10);

  $sql = "SELECT keyValue FROM ".$this->tablename;
  $result = $this->conn->query($sql);
  $keyExists=false; $i=0;
  if ($result->num_rows == 0) return $key;
  else
  while($row = $result->fetch_assoc() && $i<$result->num_rows && $keyExists==false )
  { //si la clé existe
      if($row["keyValue"]==$key) 
      {   $sql = "SELECT keyValue FROM ".$this->tablename;
          $result = $this->conn->query($sql);
          $key=substr(str_shuffle($permitted_chars), 0, 10);
          $i=-1;
      }
      $i++;
  }
  echo 'key '.$key.' generated successfully!';
   return $key;
}

}

  $restapi=new RestAPI();
 // if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['add'])) {
        // btnDelete
        $restapi->addKey($_POST['keyName']);

    } else {
        if (isset($_POST['delete']))
        {
            $restapi->deleteKey($_POST['keyName']);

        }
        { if(isset($_POST['show']))
            $restapi->showKeys();
            
        }

        }
   // }



 





	//{echo "<table>";
	//echo "<tr><td>keyName:  </td><td>$result->keyName</td></tr>";
	//echo "<tr><td>KeyValue:  </td><td>$result->keyValue</td></tr>";
	//echo "<tr><td>date:  </td><td>$result->dateTime</td></tr>";
	//echo "</table>";
	//}
	//else echo 'this keyName doesn\'t exists!';
//}
    ?>

<br />
</div>

</div>
</body>
</html>
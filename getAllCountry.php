<?php 
 //Importing Database Script 
 require_once('dbConnect.php');
 
 //Creating sql query
 $sql = "SELECT * FROM  member_country_info";
 
 //getting result 
 $r = mysqli_query($con,$sql);
 
 //creating a blank array 
 $result = array();
 
 //looping through all the records fetched
 while($row = mysqli_fetch_array($r)){
 
 //Pushing name and id in the blank array created 
 array_push($result,array(
 "country_info_auto_id"=>$row['country_info_auto_id'],
 "country_name"=>$row['country_name'],
 "member_auto_id"=>$row['member_auto_id'],
 
 ));
 }
 
 
 
 echo json_encode($result);
 
 
 
 mysqli_close($con);
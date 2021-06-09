<?php 
 //Getting Id
 //$id = $_GET['p_id'];
 
  if($_SERVER['REQUEST_METHOD']=='POST'){
 $country_info_auto_id= $_POST['country_info_auto_id'];
 //Importing database
 require_once('dbConnect.php');

 //Creating sql query
 $sql = "DELETE FROM member_country_info WHERE country_info_auto_id=$country_info_auto_id;";
 
 //Deleting record in database 
 if(mysqli_query($con,$sql)){
 echo 'Member Country Deleted Successfully';
 }else{
 echo 'Could Not Delete Member Country Try Again';
 }

 //closing connection 
 mysqli_close($con);
  }
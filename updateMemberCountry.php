<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 //Getting values
 $country_info_auto_id = $_POST['country_info_auto_id'];
 $country_name = $_POST['country_name'];
 $member_auto_id= $_POST['member_auto_id'];
 
 //importing database connection script 
 require_once('dbConnect.php');
 
 //Creating sql query 
 $sql = "UPDATE member_country_info SET country_name='$country_name',member_auto_id='$member_auto_id' WHERE country_info_auto_id='$country_info_auto_id';";
  require_once('dbConnect.php');
 //Updating database table 
 if(mysqli_query($con,$sql)){
 echo 'Member Country Updated Successfully';
 }else{
 echo 'Could Not Update Member Country Try Again';
 }
 
 //closing connection 
 mysqli_close($con);
 }
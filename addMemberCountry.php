<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Getting values
 $country_info_auto_id = $_POST['country_info_auto_id'];

 $country_name = $_POST['country_name'];

 $member_auto_id= $_POST['member_auto_id'];
 
   
   


  //Importing our db connection scripts
 require_once('dbConnect.php');
 
   
  //---FOR BANGLA INSERT-----
 
 mysqli_query($con,'SET CHARACTER SET utf8'); 
 mysqli_query($con,"SET SESSION collation_connection ='utf8_general_ci'");
 
 //----------
 
 //Creating an sql query
 $sql = " INSERT INTO  member_country_info(country_name,member_auto_id) VALUES ('$country_name','$member_auto_id')";

 //Executing query to database
 if(mysqli_query($con,$sql)){
 echo 'Country of Member Added Successfully';
 }else{
 echo 'Could Not Add Country of Member';
 }

 //Closing the database 
 mysqli_close($con);
 }
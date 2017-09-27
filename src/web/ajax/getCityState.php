<php?
    $cityState=array("81611"=>"Aspen,Colorado",
                     "81411"=>"1,1",
                     "80908"=>"1,1"
                     );
    header("Content-Type:text/plain");
    $zip=$_GET["zip"];
    if(array_key_exists($zip,$cityState)){
    	print $cityState[$zip];
    }
    else{
    	print ",";
    }
 ?>
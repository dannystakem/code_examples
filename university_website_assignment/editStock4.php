<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles.css"/>	
    <title> Full Stop - Contact Info </title>
	
<!-- Javascript Form Code -->	
	
<script type="text/javascript">

<!-- Javascript Clock -->

	function startTime() {
	
		// declaring different variables for the clock
		var today = new Date();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		
		// calling the checkTime() function for the minutes and seconds variables
		m = checkTime(m);
		s = checkTime(s);
		
		// showing what will be displayed in the header section
		document.getElementById('clock').innerHTML = h + ":" + m + ":" + s;
		
		// refreshing the clock every 500 milliseconds
		var t = setTimeout(startTime, 500);
	}
	
	function checkTime(i) {
	
		/*
		 * if the value of the minutes or seconds variables are less than 10, then 
		 * a zero is printed in front of the variable
		 */
		if (i < 10) {i = "0" + i};
		return i;
	}
	
<!-- Javascript Header Animation -->	
	
	var recordOne="pictures/header1.jpg";
	
	// function that switches between different still images
	function animate() {
	
		if (recordOne=="pictures/header1.jpg")
			recordOne="pictures/beatlesHeader.jpg";
			
		else if (recordOne=="pictures/beatlesHeader.jpg")
			recordOne="pictures/header3.jpg";
			
		else if (recordOne=="pictures/header3.jpg")
			recordOne="pictures/bowieHeader.jpg";
			
		else if (recordOne=="pictures/bowieHeader.jpg")
			recordOne="pictures/header4.jpg";
			
		else if (recordOne=="pictures/header4.jpg")
			recordOne="pictures/headerStones.jpg";
			
		else
			recordOne="pictures/header1.jpg"
		
	document.getElementById("animatePic").src = recordOne; 
	
	// animated banner refreshes every two seconds by calling the animate() function
	window.setTimeout("animate()", 2000)
	}
	
</script>
</head>

<body onload="startTime(); animate();">
    <div id= body>
	<table cellspacing = "15">
	
<!-- Header -->
	
       <tr>
            <td colspan="3" width="1400" height="100">
			<div id = "header">
                <h1 style="color:#8181F7; text-decoration:overline; padding-left:100px;">
				<img id='animatePic' src='pictures/header1.jpg' width="100" height="100">
				&nbsp;&nbsp;&nbsp;Full Stop Record Store</h1>
				<div id="clock" style="color:#8181F7; position:absolute; left: 1025px; top: 100px; font-weight:bold;"></div>
			</div>
			</td>
        </tr>
		
<!-- Navigation Area -->
		
        <tr>
            <td width="275" height="600">
			<div id="contactNav"><br><br>
                <img src="pictures/contactnav.jpg" width="225" height="225">
                <br>
                <h2 class="redtext" style="text-align: center;"> On Our Site </h2>
                <ul id="navlist">
                    <li><a href="index.html"> Home </a></li>
                    <li><a href="about.html"> About Us </a></li>
                    <li><a href="contact.html"> Contact Information </a></li>
                    <li><a href="offers.html"> Special Offers </a></li>
					<li><a href="links.html"> Useful Links </a></li>
                </ul><br>
				
				<h2>Management Only</h2>
				<form name=queries action="showContactUs.php" method="post">
				<p>User Name: (required)</p>
				<input type=text name=userName value="" size=30>
				<p>Password: (required)</p>
				<input type=password name=password value="" size=30>
				<br><br>
				<div class = "button">
				<input type="submit" name=button1 value="Submit">
				</div><br>
				</form>
				
			</div>
            </td>
			
<!-- Content Area for PHP Table -->
			
            <td width="1100" height="500">
			<div id="content">
			
			<?php

				// values posted into the page by the user via the form on the previous page.
				$id = $_POST['id'];
				$name = $_POST['name'];
				$description = $_POST['description'];
				$unit = $_POST['stock'];
				$cost = $_POST['cost'];
				
				// connect to the database with relevant information
				$link =  mysqli_connect("danu6.it.nuigalway.ie","mydb2037sd", "li7xor", "mydb2037");
				
				// SQL Statement to update the selected fields
				$sql = "UPDATE Products SET name = '$name', description = '$description', stock = '$unit', price = '$cost'  WHERE ID = '$id';";
				$link->query($sql);
			
				echo"<br><p>$name Successfully Updated</p>";
			
				// reconnect to database
				$link =  mysqli_connect("danu6.it.nuigalway.ie","mydb2037sd", "li7xor", "mydb2037");
				
				// SQL query selecting all the information from the relevant database table
				$query = "SELECT * FROM Products";
				$result = $link->query($query);
		
				echo"<br>";
				echo"<h2>Stock Table</h2>";
				echo "<table border=1>
						<tr>
							<td>ID</td>
							<td>Name</td>
							<td>Description</td>
							<td>Units</td>
							<td>Cost (Euro)</td>
						</tr>";

				// while loop used to print the table
				while ($row = mysqli_fetch_array($result)) {
					echo "<tr><td>{$row['ID']}</td>";
					echo "<td>{$row['name']}</td>";
					echo "<td>{$row['description']}</td>";
					echo "<td>{$row['stock']}</td>";
					echo "<td>{$row['price']}</td></tr>";
				}
				echo "</table><br><br>";
				
				// buttons to allow the user to edit, add or delete products
				echo"<table style=\"border:none; padding:0px;\"><tr>
						<td><form action=\"editStock2.php\">
							<input type=\"submit\" value=\"Edit Product\"></form></td>
						<td><form action=\"addProduct.html\">
							<input type=\"submit\" value=\"Add Product\"></form></td>
						<td><form action=\"delete.php\">
							<input type=\"submit\" value=\"Delete Product\"></form></td>
						</tr>
					</table>";
	
			?>
			
			</div>	
			</td>
        </tr>
		
<!-- Footer -->
		
        <tr>
            <td colspan="3" width="1400" height="150">
			<div id="footer" class="bluetext">
                <h3> Full Stop Record Store, Shop Street, Galway. </h3>
                <h3> Tel. No. (091) 123456 </h3>
			</div>
            </td>
        </tr>
    </table>
	</div>
</body>
</html>
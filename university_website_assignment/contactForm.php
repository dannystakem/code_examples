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
				<img src="pictures/navform.jpg" width="225" height="325">
			</div>
            </td>
			
<!-- Content Area -->
			
            <td width="1100" height="500">
			<div id="content">
				<br>
                <h2 class="alignleft"> Contact Information </h2>
                <dl class="alignleft">
					<img class= "floatright" src="pictures/contactmain.jpg" width="500" height="300">
					
                    <dt> Fulfilment Executive </dt>
					<dd> Name : John Smith <br>
						 Phone No. : 087-9876543 <br>
						 Email: johnsmith@gmail.com <br>
					</dd>
					<br>
					<dt> Marketing Officer </dt>
					<dd> Name : Mary Reilly <br>
						 Phone No. : 085-6587562 <br>
						 Email: maryreilly@gmail.com <br>
					</dd>
					<br>
                    <dt> Secretary </dt>
					<dd> Name : Jack Roberts <br>
						 Phone No. : 086-5486325 <br>
						 Email: jackroberts@hotmail.com <br>
					</dd>
					<br>
                </dl>
			</div><br>
			
<!-- Form Area With PHP Code -->
	
		<?php
		
		/* 
		 * declaring a php variable which is assigned the 'name' variable from the HTML contact page through 
		 * the use of POST, the length of the php variable is determined through the use of the strlen() method
		 */
		$name = $_POST['name'];
		$nameLen = strlen($name);
		
		/*
		 * the 'email' variable is accessed in the same manner as above. Two more variables are declared to find 
		 * the '@' and '.' symbols in the email string. These are found in the string using the strpos() method
		 */
		$email = $_POST['email'];
		$emailLen = strlen($email);
		$at = '@';
		$dot = '.';
		$posAt = strpos($email, $at);
		$posDot = strpos($email, $dot);
		
		/* 
		 * getting the drop down list info from the contact form through POST. An array holds the different 
		 * selections that a customer can choose, this is stored in a php variable
		 */
		$num = $_POST['list'];
		$category = array("sales", "returns", "orders", "shipping", "complaints", "other");
		
		// getting the comment box info from the contact HTML page and determining its length with strlen()
		$comment = $_POST['question'];
		$commentLen = strlen($comment);
		
		/*
         * creating php variables for receiving a customers email, as well as the subject of the email. The customers 
		 * info is formatted and assigned to the php variable $sendQuestion
		 */
		$to = "d.stakem1@nuigalway.ie";
		$subject = "Full Stop Records Customer Query";
		$sendQuestion = "$comment\r\n\r\nFrom: $name \r\nEmail: $email";
		
		// creating a php variable for the header info of the email. The customers info is formatted accordingly
		$headers = 'From: '.$name."\r\n". 
				'Reply To: '.$email."\r\n".
				'X-Mailer:PHP/'.phpversion();
		
		/*
   		 * method which recreates the form of the HTML contact page and takes in three parameters consisting of the 
		 * name, email and comment that the customer has entered, this is displayed using the printf() method
		 */
		function createForm($name, $email, $comment) {
			
			printf(
				"<div id=\"contentstyle\">
				<h2 style=\"text-align:center;\">We welcome all feedback given to our business</h2>
				<form name=\"contentForm\" method=\"post\" action=\"contactForm.php\">

				<table border=\"0\">
				<td width=\"450\" height=\"300\"><br><br>
					<img style=\"padding-bottom:100px\" src=\"pictures/form.jpg\" width=\"400\" height=\"250\">
				</td>
				<td width=\"400\" height=\"300\">
					Please enter your name:<br>
					<input type=\"text\" name=\"name\" value=\"$name\" size=\"20\">
					<br><br>Please enter your email:<br>
					<input type=\"text\" name=\"email\" value=\"$email\" size=\"20\">
				
				<br><br><label>Please select a category:</label><br>
					<select id=myList name=\"list\">
						<option value=\"0\">Sales</option>
						<option value=\"1\">Returns</option>
						<option value=\"2\">Orders</option>
						<option value=\"3\">Shipping</option>
						<option value=\"4\">Complaints</option>
						<option value=\"5\">Other</option>
					</select><br><br>
				
				Please leave a comment or question:<br>
				<textarea name=\"question\" rows=\"10\" cols=\"50\"></textarea><br><br>
				
				<div class=\"button\">
					<input type=\"submit\" name=\"button1\" value=\"Submit\">
				</div>
				</td>
				</form>
				</table>
				</form>
				</div>");
		}
			
			/*
			 * using if/else if/else statements to perform server-side validation of the customer information entered in the
			 * same manner as was done in assignment 3 with client-side validation through javascript. The lengths of the 
			 * php variables are tested, as well as the fact that the email contains an '@' character and a '.' character.
			 * The createForm() method is called in each code block so that any correctly entered info remains on the page 
			 * while other variables are being tested. If everything is valid then a string is printed on the page to inform
			 * the customer, and the information is emailed to the business through the mail() function
			 */
			if($nameLen<10){
				createForm($name, $email, $comment);
				echo("<p class=\"redtext\" align=\"right\">*** Your name must be greater than 10 characters long ***</p>");
			}
			else if($emailLen<10){
				createForm($name, $email, $comment);
				echo("<p class=\"redtext\" align=\"right\">*** Your email must be greater than 10 characters long ***</p>");
			}
			else if ($posAt === false) {
				createForm($name, $email, $comment);
				echo("<p class=\"redtext\" align=\"right\">*** Your email must contain an '@' character ***</p>");		
			} 
			else if ($posDot === false) {
				createForm($name, $email, $comment);
				echo("<p class=\"redtext\" align=\"right\">*** Your email must contain a '.' character ***</p>");
			}
			else if($commentLen<25){
				createForm($name, $email, $comment);
				echo("<p class=\"redtext\" align=\"right\">*** Your query should be greater than 25 characters long ***</p>");
			}
			else{
				echo"<h1 class=\"greentext\"><font size=\"6\">Thank you $name, someone from $category[$num] will get back to you shortly via $email</font></h1>";
				mail($to, $subject, $sendQuestion, $headers);
				
				// connecting to the database on danu6 with log in information
				$link =  mysqli_connect("danu6.it.nuigalway.ie", "mydb2037sd", "li7xor", "mydb2037") or die("connection failure");
				
				// creating a table in the database to store the relevant customer information
				$sql = "INSERT INTO CustomerQueries (name, email, comment, category) VALUES('$name', '$email', '$comment', '$category[$num]');";
				$link->query($sql);
			}	
			
		?>
		
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
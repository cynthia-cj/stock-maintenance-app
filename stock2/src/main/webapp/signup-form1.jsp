<!DOCTYPE html>
<html>

<head><title>Signup</title></head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Enter your details to register</h2>
		</div>
	</div>
	
	<div id="container">
		<form action="ItemControllerServlet" method="GET">
			<input type="hidden" name="command" value="SIGNUP">
			
			<table>
				<tbody>
					<tr>
						<td><label>Company name:</label></td>
						<td><input type="text" name="companyName"></td>
					</tr>
					
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="userName"></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td><label>No. of warehouses</label></td>
						<td><input type="number" name="noOfWarehouses"></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Continue"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
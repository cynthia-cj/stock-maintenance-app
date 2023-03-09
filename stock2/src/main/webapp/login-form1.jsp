<!DOCTYPE html>
<html>

<head><title>LOGIN</title></head>

<body>

<div id="wrapper">
	<div id="header">
		<h2>LOGIN/SIGNUP</h2>
	</div>
</div>

<div id="container">
	<div id="content">

	New User? Register here! 
	<input type="button" value="Sign Up"
		   onclick="window.location.href='signup-form1.jsp'; return false;"
	/>
	<br/><br/>
	
	<h3>Login</h3>
	
	<form action="ItemControllerServlet" method="GET">
		<input type="hidden" name="command" value="LOGIN">
		
		<table>
			<tbody>
				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="username"></td>
				</tr>
				
				<tr>
					<td><label>Password:</label></td>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Login"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</div>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>
<head><title>SignUp</title></head>

<body>

<div id="wrapper">
	<div id="header">
		<h3>Registered Successfully! Please enter the details below to continue</h3>
	</div>
</div>

<div id="container">
<form action="ItemControllerServlet" method="GET">
	<input type="hidden" name="command" value="SIGNUP2">
	<input type="hidden" name="no_of_warehouses" value="${noOfWarehouses }">
	<input type="hidden" name="username" value="${username }">s
<table>
	<tbody>
		<c:forEach var="i" begin="1" end= "${noOfWarehouses }" varStatus="loop">
			<tr>
				<td><label>Enter Location ${i }:</label></td>
				<td><input type="text" name="location${i }"></td>
			</tr>
			
			<br/>
		</c:forEach>
		<tr>
			<td><label>Login again to continue</label></td>
			<td><input type="submit" value="Save"></td>
		</tr>
	</tbody>
</table>
</form>
</div>
</body>

</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>

<head>

<title>Stock maintenance app</title>
</head>


<body>

<div id="wrapper">

	<div id="header">
	
		<h2>Create Invoice</h2>
	</div>
</div>

<div id="container">
	<form action="ItemControllerServlet" method="GET">
		<input type="hidden" name="command" value="CREATE_INVOICE">
		
		<table>
			<tbody>
				<tr>
					<td><label>From:</label></td>
					<td><input type="text" name="from"></td>
				</tr>
				
				<tr>
					<td><label>To:</label></td>
					<td><input type="text" name="to"></td>
				</tr>
				<tr>
					<td><label>Item Name:</label></td>
					<td><input type="text" name="itemName"></td>
				</tr>
				<tr>
					<td><label>Quantity:</label></td>
					<td><input type="number" name="quantity"></td>
				</tr>
				<tr>
					<td><label>Price (in rupees):</label></td>
					<td><input type="number" name="price"></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Create"></td>
			</tbody>
		</table>
	</form>
	<div style="clear:both"></div>
	<p>
	<a href="ItemControllerServlet">Back to list</a>
</div>
</body>
</html>
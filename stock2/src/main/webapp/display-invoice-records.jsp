<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>

<head><title>View Orders</title></head>

<body>

<div id="wrapper">
	<div id="header">
		<h2>View Order Records</h2>
	</div>
</div>

<div id="container">
	<form action="ItemControllerServlet" method="GET">
	
		<input type="hidden" name="command" value="VIEW_ORDERS">
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Enter Date:</label></td>
					<td><input type="date" name="date"></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="view"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<br/><br/>
	<table border="1">
	
		<tr>
			<th>Date</th>
			<th>Item Name</th>
			<th>Quantity</th>
		</tr>
		
		<c:forEach var="tempRecord" items="${ORDERS_LIST }">
		
			<tr>
				<td>${tempRecord.invoice_date }</td>
				<td>${tempRecord.itemName }</td>
				<td>${tempRecord.quantity }</td>
			</tr>
		</c:forEach>
		
	</table>
	
	<div style="clear: both;"></div>
	
	<p>
	<a href="ItemControllerServlet">Back to List</a>
</div>
</body>
</html>
				
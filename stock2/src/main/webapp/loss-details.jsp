<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>

<head><title>Loss Details</title></head>

<body>

<div id="wrapper">
	<div id="header">
		<input type="button" value="Record new Loss"
			   onclick="window.location.href='record-loss-form.jsp'; return false;"
		/>
		<br/><br/>
		
		<h3>View loss details</h3>
	</div>
</div>
<br/><br/>

<div id="container">
	<form action="ItemControllerServlet" method="GET">
	
		<input type="hidden" name="command" value="VIEW_LOSS_DETAILS">
		
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
			<th>ID</th>
			<th>Date</th>
			<th>Warehouse</th>
			<th>Item Name</th>
			<th>Quantity</th>
			<th>Reason</th>
		</tr>
		
		<c:forEach var="tempRecord" items="${LOSS_RECORDS }">
		
			<tr>
				<td>${tempRecord.id }
				<td>${tempRecord.date }</td>
				<td>${tempRecord.whName }</td>
				<td>${tempRecord.itemName }</td>
				<td>${tempRecord.quantity }</td>
				<td>${tempRecord.reason }</td>
			</tr>
		</c:forEach>
		
	</table>
	
	<div style="clear: both;"></div>
	
	<p>
	<a href="ItemControllerServlet">Back to List</a>
</div>
</body>
</html>
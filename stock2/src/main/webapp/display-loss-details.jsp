<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>

<head><title>Display Loss Records</title></head>


<body>
<div id="container">

<table border="1">
		<tr>
			<th>Record ID</th>
			<th>Date</th>
			<th>Warehouse</th>
			<th>Item Name</th>
			<th>Quantity</th>
			<th>Reason</th>
		</tr>
		
		<c:forEach var="tempLossRecord" items="${LOSS_RECORDS }">
		
			<tr>
				<td>${tempLossRecord.id }</td>
				<td>${tempLossRecord.date }</td>
				<td>${tempLossRecord.whName }</td>
				<td>${tempLossRecord.itemName }</td>
				<td>${tempLossRecord.quantity }</td>
				<td>${tempLossRecord.reason }</td>
			</tr>
		</c:forEach>
		
	</table>
	
	<div style="clear: both"></div>
	<p>
	<a href="ItemControllerServlet">Back to list</a>
</div>
</body>
</html>
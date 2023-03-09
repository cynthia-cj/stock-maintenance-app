<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<html>

<head>

<title>Stock maintenance app</title>
</head>


<body>

<div id="wrapper">

	<div id="header">
	
		<h2>Current Items</h2>
	</div>
</div>

<div id="container">

	<div id="content">
		<!-- Put the new button: Add student -->
		<input type = "button" value = "Add Item"
					  onclick= "window.location.href='add-item-form1.jsp'; return false;"
		/>
		<br/><br/>
		
		<input type = "button" value="Record orders"
					   onclick = "window.location.href='invoice-form1.jsp'; return false;"
		/>
		<br/><br/>
		
		<input type="button" value="View Order records"
					onclick = "window.location.href='display-invoice-records.jsp'; return false;"
		/>
		<br/><br/>
		<input type="button" value="Create Invoice"
					onclick="window.location.href='create-invoice-form1.jsp'; return false;"
		/>
		<br/><br/>
		
		<input type="button" value="Loss details"
			   onclick="window.location.href='loss-details.jsp'; return false;"
	    />
	    
	    <br/><br/>
	
		<table border ="1">
		
			<tr>
				<!-- <th>Item ID</th> -->
				<th>Item Name</th>
				<th>Brand</th>
				<th>Count</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="tempItem" items = "${ITEM_LIST }">
			
			<!-- set up a link for each student -->
			<c:url var="tempLink" value="ItemControllerServlet">
				<c:param name="command" value="LOAD" />
				<c:param name="itemId" value="${tempItem.item_id}"></c:param>
			</c:url>
			
			<c:url var="deleteLink" value="ItemControllerServlet">
				<c:param name="command" value="DELETE" />
				<c:param name="itemId" value="${tempItem.item_id }"></c:param>
			</c:url>
			
			
					<tr>
						<!-- <td> ${tempItem.item_id } </td> -->
						<td> ${tempItem.item_name} </td>
						<td> ${tempItem.brand } </td>
						<td> ${tempItem.count }</td>
						<td>
							<a href="${tempLink }">Update</a>
							|
							<a href="${deleteLink }"
							onclick="if(!(confirm('Delete this item?')))return false">
							Delete</a>
						</td>
					</tr>
			</c:forEach>
				
				
			
		</table>
	</div>
	<br/><br/><br/>
	
	<form action="ItemControllerServlet" method="GET">
		<input type="hidden" name="command" value="LOGOUT">
		<input type="submit" value="Logout">
 	</form>
</div>
</body>

</html>
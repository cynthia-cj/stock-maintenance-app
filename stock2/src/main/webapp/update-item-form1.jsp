<!DOCTYPE html>
<html>

<head>
	<title>Update Item</title>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>SUN WAREHOUSES</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Item</h3>
		
		<form action="ItemControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="itemId" value="${THE_ITEM.item_id }" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Item Name:</label></td>
						<td><input type="text" name="itemName"
							value="${THE_ITEM.item_name }" /></td>
					</tr>
					
					<tr>
						<td><label>Brand:</label></td>
						<td><input type="text" name="brand"
							value="${THE_ITEM.brand }" /></td>
					</tr>
					
					<tr>
						<td><label>Count:</label></td>
						<td><input type="number" name="count"
							value="${THE_ITEM.count }" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="save" /></td>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="ItemControllerServlet">Back to list</a>
		</p>
	</div>
</body>
</html>
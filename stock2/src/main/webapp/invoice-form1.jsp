<!DOCTYPE html>
<html>

<head><title>Create Invoice</title></head>

<body>

<div id="wrapper">
	<div id="header">
		<h2>Record Orders</h2>
	</div>
</div>

<div id="container">
	<form action="ItemControllerServlet" method="GET">
	
		<input type="hidden" name="command" value="INVOICE">
		
		<table>
			<tbody>
			
				<tr>
					<td><label>Date:</label></td>
					<td><input type="date" name="date"></td>
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
					<!-- <td><label></label></td>
					<td><input type="button" value="Add another item" 
							   onclick="window.location.href = 'invoice-form1.jsp'; return false;">
					</td> -->
					
					<td><input type="submit" value="Done"></td>
				</tr>
				
				
			</tbody>
		</table>
	</form>
	
	<div style="clear: both"></div>
	
	<p>
	<a href="ItemControllerServlet">Back to home</a>
</div>
</body>
</html>
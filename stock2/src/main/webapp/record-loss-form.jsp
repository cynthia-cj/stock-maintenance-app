<!DOCTYPE html>
<html>

<head><title>Record Loss</title></head>

<body>

<div id="wrapper">
	<div id="header">
		<h3>Record Loss</h3>
	</div>
</div>

<div id="container">
	<form action="ItemControllerServlet" method="GET">
		<input type="hidden" name="command" value="RECORD_LOSS">
		
		<table>
		
			<tbody>
			
				<tr>
					<td><label>Date:</label></td>
					<td><input type="date" name="date"></td>
				</tr>
				<tr>
					<td><label>Warehouse Name:</label></td>
					<td><input type="text" name="wh_name"></td>
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
					<td><label>Reason:</label></td>
					<td><input type="text" name="reason"></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="clear: both"></div>
	<p>
	<a href="ItemControllerServlet">Back to list</a>
	
</div>
</body>
</html>
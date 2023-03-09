<!DOCTYPE html>
<html>

<head><title>Add Item</title></head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>WAREHOUSE</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Item</h3>
		
		<form action="ItemControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<!--  <tr>
						<td><label>Item ID: </label></td>
						<td><input type="text" name="itemId"></td>
					</tr>
					-->
					<tr>
						<td><label>Item Name: </label></td>
						<td><input type="text" name="itemName"></td>
					</tr>
					
					<tr>
						<td><label>Brand: </label></td>
						<td><input type="text" name="brand"></td>
					</tr>
					
					<tr>
						<td><label>Count </label></td>
						<td><input type="number" name="count"></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" name="save"></td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
		<a href="ItemControllerServlet">Back to List</a>
			
	</div>
</body>
</html>
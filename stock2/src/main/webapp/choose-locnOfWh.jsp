<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>

<head><title>Choose Warehouse</title></head>

<body>

<h3>Select the warehouse to operate</h3>


<c:forEach var="tempLocn" items="${LOCATIONS }">
 
	<c:url var="tempLink" value="ItemControllerServlet">
		<c:param name="command" value="LIST"></c:param>
		<c:param name="location" value="${tempLocn }"></c:param>
	</c:url>
	
	<a href="${tempLink}">"${tempLocn}"</a>
</c:forEach>


</body>
</html>
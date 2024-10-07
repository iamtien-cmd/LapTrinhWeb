<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/result" enctype="multipart/form-data">
<c:if test="${alert !=null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
</body>
</html>
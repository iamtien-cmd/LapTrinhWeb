<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- <form action="<c:url value='${pageContext.request.contextPath}/admin/category/insert'></c:url>"--%>
<form action="${pageContext.request.contextPath}/admin/category/insert"
	method="post" enctype="multipart/form-data">
	<label for="fname">Category name:</label> <br> <input type="text"
		id="categoryname" name="categoryname" value=""><br> 
		<label for="image">Images name:</label> <br>

	<c:if test="${cate.images.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${cate.images.substring(0,5)  == 'https'}">
		<c:url value="${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" id="imagess" /> <input
		type="file" onchange="chooseFile(this)" id="imagess" name="images"
		value=""><br> <label for="active">Active</label> <br>
	<input type="text" id="active" name="active" value="${cate.active}"><br>

	<input type="submit" value="Insert">
</form>

<!--  enctype="multipart/form-data" -->
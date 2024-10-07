<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action="${pageContext.request.contextPath}/admin/category/update"
      method="post" enctype="multipart/form-data">
	<input type="text"
		id="categoryid" name="categoryid" value="${cate.categoryid}" hidden="hidden"><br>
	<label for="fname">Category name:</label> <br> 
	<input type="text"
		id="categoryname" name="categoryname" value="${cate.categoryname}"><br>
	<label for="lname">Images name:</label> <br>
	<c:if test="${cate.images.substring(0,5)  != 'https'}">
		<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${cate.images.substring(0,5)  == 'https'}">
		<c:url value="${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" id="imagess" />
	<input
		type="file" onchange="chooseFile(this)" id="imagess" name="images"><br>
	<label for="active">Active</label> <br> <input type="text"
		id="active" name="active" value="${cate.active}"><br> <input
		type="submit" value="Sửa">
</form>

<!--  enctype="multipart/form-data" -->
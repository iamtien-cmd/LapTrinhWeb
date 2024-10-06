<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/baitap/ForgetPass" method="post">
<h1>Quên mật khẩu</h1><br>
 <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>
 <input
						type="text" placeholder="Tài khoản" name="username"
						class="form-control"> <br> <input type="text"
						placeholder="Mật khẩu mới" name="newpassword" class="form-control">
					<br> 
					<input type="text"
						placeholder="Xác nhân mật Khẩu" name="confirmpassword" class="form-control">
					<br> 
					<input
						type="submit" value="Đổi mật khẩu">
</body>
</html>
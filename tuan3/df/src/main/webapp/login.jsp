<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login" method="post">
    <h2>Đăng nhập</h2>
    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>
    <section>
        <label class="input login-input">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                
            </div>
        </label>
          <input type="text" placeholder="Tài khoản" name="username" class="form-control"> <br/>
          <input type="text" placeholder="Mật khẩu" name="password" class="form-control"> 
                 <input type="submit" value=" Login ">
    </section>
</form>
</body>
</html>

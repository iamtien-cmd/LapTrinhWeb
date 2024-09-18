<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="vn.iotstar.utils.Constant" %>

<%
    // Lấy giá trị cookie
    Cookie[] cookies = request.getCookies();
    String username = "";
    String password = "";
    boolean remember = false;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                username = cookie.getValue();
                remember = true;
            }
            if (Constant.COOKIE_PASSWORD.equals(cookie.getName())) {
                password = cookie.getValue();
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <!-- Form đăng nhập -->
    <form action="${pageContext.request.contextPath}/login" method="post">
        <h2>Đăng nhập</h2>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

        <section>
            <label class="input login-input">
                <div class="input-group">
                    <!-- Nhập tài khoản -->
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control"
                           value="<%= username %>" required /><br>

                    <!-- Nhập mật khẩu -->
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control"
                           value="<%= password %>" required /><br>

                    <!-- Checkbox "Nhớ tôi" -->
                    <input type="checkbox" name="remember" <%= remember ? "checked" : "" %> value="ON"> Nhớ tôi <br>

                    <!-- Link đến trang quên mật khẩu -->
                    <li><a href="${pageContext.request.contextPath}/views/ForgetPass.jsp">Quên mật khẩu</a></li>

                    <!-- Nút đăng nhập -->
                    <input type="submit" value="Login">
                </div>
            </label>
        </section>
    </form>
</body>
</html>

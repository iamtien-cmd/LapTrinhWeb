<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Người Dùng</title>
</head>
<body>
        <c:choose>
            <!-- Nếu người dùng chưa đăng nhập -->
            <c:when test="${sessionScope.account == null}">
   
                    <ul class="list-inline right-topbar pull-right">
                        <li>
                            <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> | 
                            <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                        </li>
                        <li>
                            <i class="search fa fa-search search-button"></i>
                        </li>
                    </ul>
              
            </c:when>
          </c:choose>
</body>
</html>

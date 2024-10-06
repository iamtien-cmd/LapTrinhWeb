<%@page import="vn.iotstar.service.impl.CategoryServiceImpl"%>
<%@page import="vn.iotstar.service.ICategoryService"%>
<%@page import="vn.iotstar.model.CategoryModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>






	
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:redirect url="/views/topbar.jsp" />
<%
ICategoryService service = new CategoryServiceImpl();
List<CategoryModel> list = service.getAll();
request.setAttribute("listcate", list);
%>
<jsp:forward page="/views/topbar.jsp" />
<!-- <c:redirect url="/views/topbar.jsp" /> -->
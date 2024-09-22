<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Form</title>
</head>
<body>
<h2>Upload a File</h2>
<form method="post" action="${pageContext.request.contextPath}/multiPartServlet" enctype="multipart/form-data">
    <div>
    <input type="text" placeholder="Fullname" name="fullname"
						class="form-control"> <br /> 
						 <input type="text" placeholder="Phone" name="phone"
						class="form-control"> <br /> 
        <label for="fileUpload">Select file to upload:</label>
        <br />
        <input type="file" name="fileUpload" id="fileUpload" />
    </div>
    <br />
    <div>
        <label for="name">Name:</label>
        <br />
        <input type="text" name="name" id="name" size="100" />
    </div>
    <br />
    <div>
        <input type="submit" value="Upload" />
    </div>
</form>
</body>
</html>

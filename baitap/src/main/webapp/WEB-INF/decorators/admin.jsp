<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/" var="URL"></c:url>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
</head>
<body>
    <sitemesh:write property="body" />

    <input type="file" accept="image/*" onchange="chooseFile(this)" />
    <img id="imagess" src="#" alt="Selected Image" style="display:none;" />

    <script>
        function chooseFile(fileInput) {
            if (fileInput.files && fileInput.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#imagess').attr('src', e.target.result); 
                };
                reader.readAsDataURL(fileInput.files[0]);
            }
        }
    </script>
</body>
</html>

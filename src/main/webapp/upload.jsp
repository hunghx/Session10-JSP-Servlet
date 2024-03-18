<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 3/18/2024
  Time: 1:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Upload file</h1>
<form action="/UploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="images" multiple>
    <br>
    <input type="submit" value="Upload">
</form>
</body>
</html>

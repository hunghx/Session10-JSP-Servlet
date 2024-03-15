<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 3/15/2024
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Cap nhat thông tin</h1>
<form action="/todo-jsp" method="post">
    <div>
        <label for="id">ID</label>
        <input type="text" id="id" name="id" value="${todoEdit.id}" readonly>
    </div>
    <div>
        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="${todoEdit.title}">
    </div>
    <div>
        <label for="content">Content</label>
        <textarea id="content" name="content">${todoEdit.content}</textarea>
    </div>
    <div>
        <label for="status">Status</label>
        <select name="status" id="status">
            <option ${todoEdit.status?"selected":""} value="true">Đã hoàn thành</option>
            <option ${todoEdit.status?"":"selected"} value="false">CHưa hoàn thành</option>
        </select>
    </div>
    <div><input type="submit" value="UPDATE" name="action"></div>
</form>
</body>
</html>

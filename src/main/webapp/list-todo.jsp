<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 3/15/2024
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Danh sách sông việc</h1>
<a href="/todo-jsp?action=ADD">Add to do</a>
<table border="10" cellspacing="10" cellpadding="10">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Content</th>
        <th>Status</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todoList}" var="todo">
        <tr>
            <td>${todo.id}</td>
            <td>${todo.title}</td>
            <td>${todo.content}</td>
            <td>${todo.status?"Đã hoàn thanh":"Chưa hoàn thành"}</td>
            <td><a href="?action=EDIT&id=${todo.id}">EDIT</a></td>
            <td><a onclick="return confirm('bạn có chắc muốn xóa không')" href="?action=DELETE&id=${todo.id}">DELETE</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>

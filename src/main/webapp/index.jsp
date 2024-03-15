<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%-- có 3 cachs nhung jav trong jsp--%>
<%--
c1 kkhai báo biến  : <%!%>
c2 thực thi 1 biểu thức : <%=%>
c3 code logic : <%%>
--%>
<%!
    int[] array = {1,2,3,4,5};
%>
<%
    for (int number : array){
        System.out.println(number);
    }
%>
<html>
<head>
    <title><%=Arrays.stream(array).sum()%></title>
</head>
<body>

<%--nhúng jsp--%>
<jsp:include page="header.jsp"/>

<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="/todo?action=GETALL" >Danh sach công việc</a>
<a href="/todo-jsp?action=GETALL" >Danh sach công việc (JSP)</a>
<%--the a phương thức get -> doGet--%>

<%-- out --%>
<c:out value="${'hunghx'.concat('khanh son')}"/>
<%--set--%>
<c:set var="name" value="Ngu" scope="page"/>
<c:out value="${name}"/>
<%--if--%>
<c:if test="${4>3}">
    <h1>4 lớn hơn 3</h1>
</c:if>
<%-- chose when otherwise--%>
<c:choose>
    <c:when test="${name.length()<6}">
        <p>bé hơn 6 kí tự</p>
    </c:when>
    <c:when test="${name.length()<10}">
        <p>lớn hon 6 và bé hơn 10 kí tự</p>
    </c:when>
    <c:otherwise>
        <p>Lớn hơn hoặc bảng 10</p>
    </c:otherwise>
</c:choose>

<%--vngf lặp--%>
<c:forEach items="<%=array%>"  var="number" varStatus="loop">
    <p>${number}</p>
    <span>curent : ${loop.current}</span>
    <span>index : ${loop.index}</span>
    <span>count : ${loop.count}</span>
    <span>step : ${loop.step}</span>

</c:forEach>

<%--vòng lặp for i --%>
<c:forEach begin="1" end="10" step="1" varStatus="loop">
    <span>curent : ${loop.current}</span>
    <span>index : ${loop.index}</span>
    <span>count : ${loop.count}</span>
    <span>step : ${loop.step}</span>
</c:forEach>



</body>
</html>
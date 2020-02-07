<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>Welcome to create user page</title>
<body>
<H2>Welcome to create user page</H2>
<form action="${pageContext.request.contextPath}/add_user" method="post">
            <c:if test="${not empty errors}">
                <c:forEach items="${errors}" var="error">
                    <c:out value="${error}"/>
                </c:forEach>
            </c:if>
        <br>
    Name:
        <br>
            <input type="text" name="name" required maxlength="15" pattern="[A-Za-zА]{1,15}" placeholder="Only eng letters">
        <br>
    Password:
        <br>
            <input type="password" name="password" required maxlength="9" pattern="^[a-zA-Z0-9]+$" placeholder="Only eng letters and numbs">
        <br>
    Is active:
        <br>
            <input type="text" name="isActive" required maxlength="5" pattern="true|false" placeholder="true or false">
        <br>
    Age:
        <br>
            <input type="number" name="age" maxlength="3" min="1" max="200" pattern="^[0-9]+$" placeholder="1-200">
        <br>
    Address:
        <br>
            <input type="text" name="address" required maxlength="30" pattern="^[a-zA-Z0-9\s]+$" placeholder="Only eng letters">
        <br>
    Telephone:
        <br>
            <input type="text" name="telephone" required maxlength="18" pattern="[0-9]{,18}" placeholder="Max length">
        <br>
        <br>
            <input type="submit" value="Submit">
</body>
</html>
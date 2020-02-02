<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h2>Welcome to users page</h2>
    <table>
        <thead>
            <tr>
                <td>ID</td>
                <td>Username</td>
                <td>Password</td>
                <td>Is Active</td>
                <td>Age</td>
                <td>Address</td>
                <td>Telephone</td>
                <td>Delete user</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <c:choose>
                        <c:when test="${user.isActive == 'true'}">
                            <td>I am a superman</td>
                        </c:when>
                        <c:otherwise>
                            <td>Staying at shadow</td>
                        </c:otherwise>
                    </c:choose>
                    <td><c:out value="${user.age}"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.telephone}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/delete?id=${user.id}">Delete </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<p><a href="${pageContext.request.contextPath}/add_user">Go to create user page</a></p>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h2>You added user: </h2>
    <table>
        <thead>
            <tr>
                <td>username</td>
                <td>password</td>
                <td>isActive</td>
                <td>age</td>
                <td>address</td>
                <td>telephone</td>
            </tr>
        </thead>
        <tbody>
                <tr>
                    <td><c:out value="${added_user.username}"/></td>
                    <td><c:out value="${added_user.password}"/></td>
                    <td>"${added_user.isActive}"</td>
                    <td><c:out value="${added_user.age}"/></td>
                    <td><c:out value="${added_user.address}"/></td>
                    <td><c:out value="${added_user.telephone}"/></td>
                </tr>
        </tbody>
    </table>
</body>
</html>
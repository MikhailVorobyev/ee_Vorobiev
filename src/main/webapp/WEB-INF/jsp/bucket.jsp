<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:useBean id="user" type="com.accenture.flowershop.model.User" scope="request"/>
    <title>Title</title>
</head>
<body>
<section>
    <h1 align="center">Корзина</h1>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Название</th>
            <th>Цена</th>
            <th>Сумма</th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <jsp:useBean id="flower" type="com.accenture.flowershop.to.OrderTo"/>
            <tr>
                <td>${flower.name}</td>
                <td>${flower.price}</td>
                <td>${flower.sum}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>

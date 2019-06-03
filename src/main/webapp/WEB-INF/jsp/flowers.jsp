<%--
  Created by IntelliJ IDEA.
  User: mikhail.vorobiev
  Date: 31.05.2019
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Flowers</title>
</head>
<body>
    <section>
        <br><br><br>
        <h1 align="center">Каталог цветов</h1>
        <table align="center" border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество на складе</th>
            </tr>
            <c:forEach items="${flowers}" var="flower">
                <jsp:useBean id="flower" type="com.accenture.flowershop.model.Flower"/>
                <tr>
                    <td>${flower.name}</td>
                    <td>${flower.price}</td>
                    <td>${flower.quantity}</td>
                </tr>
            </c:forEach>
        </table>
    </section>
</body>
</html>

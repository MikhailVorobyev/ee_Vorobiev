<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Admin Page</title>
</head>
<body>
<div class="section">
    <div class="header">
        <p>Привет: <u>${user.login}</u></p>
        <p>Баланс: <u>${user.moneyBalance}р.</u></p>
        <p>Скидка: <u>${user.discount}%</u></p>
        <button class="btn button"><a href="exit">Exit</a></button>
    </div>
</div>

<div>
    <div class="col-mb-6">
        <table class="table table-bordered">
            <h2 align="center">Список заказов</h2>
            <thead>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Адрес</th>
                <th>Телефон</th>
                <th>Сумма</th>
                <th>Дата создания</th>
                <th>Дата закрытия</th>
                <th>Статус</th>
                <th>Кнопка закрытия</th>
            </tr>
            </thead>
            <c:forEach items="${allOrders}" var="order">
                <jsp:useBean id="order" type="com.accenture.flowershop.model.Order"/>
                <tr>
                    <td>${order.userName}</td>
                    <td>${order.userSurname}</td>
                    <td>${order.userAddress}</td>
                    <td>${order.userPhone}</td>
                    <td>${order.sum}</td>
                    <td>${order.createDate}</td>
                    <td>${order.closeDate == null ? "" : order.closeDate}</td>
                    <td>${order.status}</td>
                    <c:choose>
                        <c:when test="${order.closeDate != null || order.status == 'CREATED'}">
                            <td><input class="btn btn-success btn-sm"
                                       type="button" value="Закрыть" disabled></td>

                        </c:when>
                        <c:otherwise>
                            <td><input class="btn btn-success btn-sm" type="button"
                                       onclick="closeOrder(${order.id})" value="Закрыть"></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script>
    function closeOrder(OrderId) {
        console.log("CLOSE METHOD!!!!!!!!!!");
        document.getElementById("orderId").value = OrderId;
        document.getElementById("closeForm").submit();
    }
</script>

<%--Close Order Form--%>
<form method="post" action="closeOrder" id="closeForm">
    <input type="hidden" name="orderId" id="orderId"/>
</form>

</body>
</html>

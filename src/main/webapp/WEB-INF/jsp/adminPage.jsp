<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <jsp:useBean id="user" type="com.accenture.flowershop.to.UserTo" scope="session"/>
    <title>Admin Page</title>
</head>
<body>
<div class="section">
    <div class="header">
        <p>Привет: <u>${user.login}</u></p>
        <p>Баланс: <u>${user.moneyBalance}р.</u></p>
        <p>Скидка: <u>${user.discount}%</u></p>
        <a href="exit"><button class="btn button">Exit</button></a>
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
            <c:forEach items="${allOrders}" var="orderEntry">
                <jsp:useBean id="orderEntry"
                             type="java.util.Map.Entry<java.lang.Integer, com.accenture.flowershop.to.OrderTo>"/>
                <tr>
                    <td>${orderEntry.value.user.firstName}</td>
                    <td>${orderEntry.value.user.lastName}</td>
                    <td>${orderEntry.value.user.address}</td>
                    <td>${orderEntry.value.user.phoneNumber}</td>
                    <td>${orderEntry.value.sum}</td>
                    <td>${orderEntry.value.createDate}</td>
                    <td>${orderEntry.value.closeDate == null ? "" : orderEntry.value.closeDate}</td>
                    <td>${orderEntry.value.status}</td>
                    <c:choose>
                        <c:when test="${orderEntry.value.closeDate != null || orderEntry.value.status == 'CREATED'}">
                            <td><input class="btn btn-success btn-sm"
                                       type="button" value="Закрыть" disabled></td>

                        </c:when>
                        <c:otherwise>
                            <td><input class="btn btn-success btn-sm" type="button"
                                       onclick="closeOrder(${orderEntry.value.id})" value="Закрыть"></td>
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

<%--Close OrderTo Form--%>
<form method="post" action="closeOrder" id="closeForm">
    <input type="hidden" name="orderId" id="orderId"/>
</form>

</body>
</html>

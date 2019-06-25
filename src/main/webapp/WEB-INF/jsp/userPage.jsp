<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <jsp:useBean id="user" type="com.accenture.flowershop.model.User" scope="session"/>
    <title>User Page</title>
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

<div class="row-mb-2">
    <div class="col-mb-6 display: dis-inline-block">
        <table id="flowers" class="table table-bordered">
            <h2 align="center">Каталог цветов</h2>
            <thead>
            <tr>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество на складе</th>
                <th>Добавить в корзину</th>
            </tr>
            </thead>
            <c:forEach items="${flowers}" var="flower">
                <jsp:useBean id="flower" type="com.accenture.flowershop.model.Flower"/>
                <tr>
                    <td>${flower.name}</td>
                    <td>${flower.price}</td>
                    <td>${flower.quantity}</td>
                    <td><input id="quantity${flower.name}" type="number" name="quantity" value="0">
                        <input class="btn btn-success btn-sm" type="button" name="${flower.name}"
                               onclick="addToBucket(name, ${flower.price},
                                       document.getElementById('quantity${flower.name}').value,
                                   ${flower.quantity})" value="В корзину"></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-mb-6 display: dis-inline-block">
        <table id="bucket" class="table table-bordered">
            <h2 align="center">Корзина</h2>
            <tr>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Сумма</th>
            </tr>

            <tr id="sum">
                <td colspan="2">Общая сумма co скидкой:</td>
                <td id="sumValue" colspan="2">0</td>
            </tr>
        </table>
    </div>
</div>


<div style="margin: 50px 150px">
    <h2 align="center">Заказы</h2>
    <table id="order" class="table table-bordered">
        <thead>
        <tr>
            <th>Сумма</th>
            <th>Дата создания</th>
            <th>Дата закрытия</th>
            <th>Статус</th>
            <th>Кнопка оплаты</th>
        </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
            <jsp:useBean id="order" type="com.accenture.flowershop.model.Order"/>
            <tr>
                <td>${order.sum}</td>
                <td>${order.createDate}</td>
                <td>${order.closeDate == null ? "" : order.closeDate}</td>
                <td>${order.status}</td>
                <c:choose>
                    <c:when test="${!order.payed}">
                        <td><input class="btn btn-success btn-sm" type="button"
                                   onclick="pay(${user.moneyBalance}, ${order.sum}, ${order.id})"
                                   value="Оплатить"></td>
                    </c:when>
                    <c:otherwise>
                        <td><input class="btn btn-success btn-sm" type="button"
                                   value="Оплатить" disabled></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
    let discount = 1 - (parseFloat(${user.discount}) / 100);
    let globalSum = 0;
    let flowerData = "";

    function addToBucket(Name, Price, Amount, MaxAmount) {
        if (document.getElementById(Name) != null) {
            return;
        }
        let element = document.getElementById("bucket");
        let tr = document.createElement("tr");
        tr.setAttribute('id', "trId");
        let sum;
        if (Amount !== "" && Amount > 0 && Amount <= MaxAmount) {
            let name = document.createElement("td");
            name.innerText = Name;
            tr.appendChild(name);

            let price = document.createElement("td");
            price.innerText = Price;
            tr.appendChild(price);

            let amount = document.createElement("td");
            amount.innerText = Amount;
            tr.appendChild(amount);

            sum = document.createElement("td");
            sum.innerText = Amount * Price;

            flowerData += Name + "-" + Amount + ";";

            globalSum += parseInt(sum.innerText);
            document.getElementById("sumValue").innerText = parseInt(globalSum * discount);
            tr.appendChild(sum);
            tr.id = Name;
            element.lastChild.insertBefore(tr, document.getElementById("sum"));


            if (document.getElementById("createOrder") !== null) {
                return;
            }

            /*Order button*/
            let trOrder = document.createElement("tr");
            let tdOrder = document.createElement("td");
            tdOrder.setAttribute('colspan', '4');
            let button = tdOrder.appendChild(document.createElement("input"));
            tdOrder.lastChild.id = "createOrder";
            tdOrder.lastChild.value = "Создать заказ";
            tdOrder.lastChild.type = "button";
            button.className = "btn btn-success btn-sm";
            button.setAttribute('onClick', "createOrder()");
            trOrder.appendChild(tdOrder);
            element.lastChild.appendChild(trOrder);
        }
    }

    function createOrder() {
        let totalSum = document.getElementById("sumValue").innerText;
        document.getElementById("flowerData").value = flowerData;
        document.getElementById("orderSum").value = totalSum;
        document.getElementById("createForm").submit();
    }

    function pay(MoneyBalance, OrderSum, OrderId) {
        if (MoneyBalance >= OrderSum) {
            document.getElementById("orderId").value = OrderId;
            document.getElementById("newBalance").value = MoneyBalance - OrderSum;
            document.getElementById("payForm").submit();
        }
    }
</script>

<%--Create Order Form--%>
<form method="post" action="createOrder" id="createForm">
    <input type="hidden" name="flowerData" id="flowerData">
    <input type="hidden" name="orderSum" id="orderSum"/>
    <input type="hidden" name="userLogin" value="${user.login}">
</form>

<%--Pay Order Form--%>
<form method="post" action="payOrder" id="payForm">
    <input type="hidden" name="orderId" id="orderId"/>
    <input type="hidden" name="userLogin" value="${user.login}">
    <input type="hidden" name="newBalance" id="newBalance">
</form>

</body>
</html>

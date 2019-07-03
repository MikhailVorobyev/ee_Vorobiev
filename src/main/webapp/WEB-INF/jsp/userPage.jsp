<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <jsp:useBean id="user" type="com.accenture.flowershop.to.UserTo" scope="session"/>
    <title>User Page</title>
</head>
<body>
<div class="section">
    <div class="header">
        <p>Привет: <u>${user.login}</u></p>
        <p>Баланс: <u>${user.moneyBalance}р.</u></p>
        <p>Скидка: <u>${user.discount}%</u></p>
        <a href="exit">
            <button class="btn button">Exit</button>
        </a>
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
        <c:forEach items="${orders}" var="orderEntry">
            <jsp:useBean id="orderEntry"
                         type="java.util.Map.Entry<java.lang.Integer, com.accenture.flowershop.to.OrderTo>"/>

            <tr>
                <td>${orderEntry.value.sum}</td>
                <td>${orderEntry.value.createDate}</td>
                <td>${orderEntry.value.closeDate == null ? "" : orderEntry.value.closeDate}</td>
                <td>${orderEntry.value.status}</td>
                <c:choose>
                    <c:when test="${!orderEntry.value.payed}">
                        <td><input class="btn btn-success btn-sm" type="button"
                                   onclick="pay(${orderEntry.value.sum}, ${orderEntry.value.id})"
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

            //TODO: change int to double
            document.getElementById("sumValue").innerText = (parseFloat(globalSum * discount)).toFixed(2);
            tr.appendChild(sum);
            tr.id = Name;
            element.lastChild.insertBefore(tr, document.getElementById("sum"));


            if (document.getElementById("createOrder") !== null) {
                return;
            }

            /*OrderTo button*/
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

    function pay(OrderSum, OrderId) {
        document.getElementById("orderId").value = OrderId;
        document.getElementById("orderSumToPay").value = OrderSum;
        document.getElementById("payForm").submit();
    }

</script>

<%--Create OrderTo Form--%>
<form method="post" action="createOrder" id="createForm">
    <input type="hidden" name="flowerData" id="flowerData">
    <input type="hidden" name="orderSum" id="orderSum"/>
    <input type="hidden" name="userLogin" value="${user.login}">
</form>

<%--Pay OrderTo Form--%>
<form method="post" action="payOrder" id="payForm">
    <input type="hidden" name="orderId" id="orderId"/>
    <input type="hidden" name="userLogin" value="${user.login}">
    <input type="hidden" name="orderSumToPay" id="orderSumToPay">
</form>

</body>
</html>

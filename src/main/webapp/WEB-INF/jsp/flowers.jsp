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
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="user" type="com.accenture.flowershop.model.User" scope="request"/>
    <title>Flowers</title>
</head>
<body>
<div class="section">
    <div class="header">
        <p>Привет: <u>${user.login}</u></p>
        <p>Баланс: <u>${user.moneyBalance}р.</u></p>
        <p>Скидка: <u>${user.discount}%</u></p>
        <button class="btn button">Exit</button>
    </div>

</div>
<%--<table>
    <tr>
        <td width="400"><h2>Привет: <u>${user.login}</u></h2></td>
        <td width="300"><h2>Баланс: <u>${user.moneyBalance}р.</u></h2></td>
        <td width="300"><h2>Скидка: <u>${user.discount}%</u></h2></td>
        <td><h2><input type="button" value="Exit" style="width: 120px; height: 60px; font-size: 55px"></h2></td>
    </tr>
</table>
<hr>--%>
<section>
    <table>
        <tr>
            <td width="500">
                <h1 align="center">Корзина</h1>
                <table id="bucket" border="1" cellpadding="20" cellspacing="0">
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
            </td>
            <td>
                <h1 align="center">Каталог цветов</h1>
                <form method="post" action="flower">
                    <table align="center" border="1" cellpadding="8" cellspacing="0">
                        <tr>
                            <th>Название</th>
                            <th>Цена</th>
                            <th>Количество на складе</th>
                            <th>Добавить в корзину</th>
                        </tr>
                        <c:forEach items="${flowers}" var="flower">
                            <jsp:useBean id="flower" type="com.accenture.flowershop.model.Flower"/>
                            <tr>
                                <td>${flower.name}</td>
                                <td>${flower.price}</td>
                                <td>${flower.quantity}</td>
                                <td><input id="quantity${flower.name}" type="number">
                                    <input type="button" name="${flower.name}" onclick="addToBucket(name,
                                        ${flower.price}, document.getElementById('quantity${flower.name}').value,
                                        ${flower.quantity})" value="В корзину"></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
                <br>
                <table align="center" border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <td>Поиск по названию:</td>
                        <td colspan="2"><input type="text" name="name" size="56"></td>
                    </tr>
                    <tr>
                        <td>Поиск по диапазону:</td>
                        <td>От: <input type="number" name="from"></td>
                        <td>До: <input type="number" name="to"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</section>
<section>
    <h1 align="center">Заказы</h1>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Сумма</th>
            <th>Дата создания</th>
            <th>Дата закрытия</th>
            <th>Статус</th>
        </tr>
    </table>
</section>
<script>
    let discount = 1 - (parseFloat(${user.discount}) / 100);
    let globalSum = 0;

    function addToBucket(Name, Price, Amount, MaxAmount) {
        if (document.getElementById(Name) != null) {
            return;
        }
        let element = document.getElementById("bucket");
        let tr = document.createElement("tr");
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

            globalSum += parseInt(sum.innerText);
            document.getElementById("sumValue").innerText = globalSum * discount;
            console.log(discount);
            tr.appendChild(sum);
            tr.id = Name;
            element.lastChild.insertBefore(tr, document.getElementById("sum"));
        }
        if (document.getElementById("createOrder") !== null) {
            return;
        }
        let trOrder = document.createElement("tr");
        let tdOrder = document.createElement("td");
        tdOrder.setAttribute('colspan', '4');
        tdOrder.appendChild(document.createElement("input"));
        tdOrder.lastChild.id = "createOrder";
        tdOrder.lastChild.value = "Создать заказ";
        tdOrder.lastChild.type = "button";
        trOrder.appendChild(tdOrder);
        element.lastChild.appendChild(trOrder);
    }
</script>
</body>
</html>

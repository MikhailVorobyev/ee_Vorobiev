<%--
  Created by IntelliJ IDEA.
  User: mikhail.vorobiev
  Date: 03.06.2019
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Registration</title>
</head>
<body>
<br><br><br><br><br><br><br><br>
<div align="center">
    <h3>Регистрация</h3>
    <form method="post" action="user">
        <input type="hidden" name="form" value="registration">
        <dl>
            <dt>Логин:</dt>
            <dd><input type="text" name="login" size=30></dd>
        </dl>
        <dl>
            <dt>Пароль:</dt>
            <dd><input type="text" name="password" size=30></dd>
        </dl>
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="name" size=30></dd>
        </dl>
        <dl>
            <dt>Фамилия:</dt>
            <dd><input type="text" name="surname" size=30></dd>
        </dl>
        <dl>
            <dt>Адрес:</dt>
            <dd><input type="text" name="address" size=30></dd>
        </dl>
        <dl>
            <dt>Телефон:</dt>
            <dd><input type="text" name="phone" size=30></dd>
        </dl>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</div>

</body>
</html>

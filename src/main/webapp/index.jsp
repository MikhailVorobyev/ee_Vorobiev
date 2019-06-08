<%--
  Created by IntelliJ IDEA.
  User: mikhail.vorobiev
  Date: 29.05.2019
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Authorization</title>
</head>
<body>
<br><br><br><br><br><br><br><br>
<div align="center">
    <h3><a href="user?action=registration">Регистрация</a></h3>
    <form method="post" action="user">
        <input type="hidden" name="form" value="authorise">
        <dl>
            <dt>Login:</dt>
            <dd><input type="text" name="login" size=30></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="text" name="password" size=30></dd>
        </dl>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>

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
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Registration</title>
</head>
<body>
<%--<br><br><br><br><br><br><br><br>
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
</div>--%>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w" method="post" action="user">
                <input type="hidden" name="form" value="registration">
                <span class="login100-form-title p-b-51">Регистрация</span>

                <div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
                    <input class="input100" type="text" name="login" placeholder="Логин" required>
                    <span class="focus-input100"></span>
                </div>


                <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                    <input class="input100" name="password" placeholder="Пароль" required>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16">
                    <input class="input100" name="name" placeholder="Имя" required>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16">
                    <input class="input100" name="surname" placeholder="Фамилия">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16">
                    <input class="input100" name="address" placeholder="Адрес">
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16">
                    <input class="input100" name="phone" placeholder="Телефон">
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <button class="login100-form-btn" type="submit">Сохранить</button>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <button class="login100-form-btn" type="reset">Отменить</button>
                </div>

            </form>
        </div>
    </div>
</div>
<div id="dropDownSelect1"></div>


</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Registration</title>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w" method="post" action="registration">
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

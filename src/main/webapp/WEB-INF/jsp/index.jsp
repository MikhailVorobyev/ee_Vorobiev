<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="headTag.jsp"/>
    <title>Authorization</title>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w" method="post" action="authorisation">
                <input type="hidden" name="form" value="authorise">
					<span class="login100-form-title p-b-51">
						<a style="font-size: 40px" href="${pageContext.request.contextPath}/registration"><u>Регистрация</u></a>
					</span>


                <div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
                    <input class="input100" type="text" name="login" placeholder="Username">
                    <span class="focus-input100"></span>
                </div>


                <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                    <input class="input100" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <button class="login100-form-btn" type="submit">Submit</button>
                </div>

            </form>
        </div>
    </div>
</div>
<div id="dropDownSelect1"></div>

</body>
</html>

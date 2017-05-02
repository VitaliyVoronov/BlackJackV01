<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/loginAndRegistration.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row">
        <img src="/resources/images/blackjack2.png" id="blackjackimg">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="/" id="login-form-link1">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" class="active" id="register-form-link1">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    ${message}
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form commandName="player" id="register-form" action="/signUp" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <form:input path="name" type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value=""/>
                                </div>
                                <div class="form-group">
                                    <form:input path="email" type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value=""/>
                                </div>
                                <div class="form-group">
                                    <form:input path="password" type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"/>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Sign up">
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<div class="container">--%>
    <%--<div class="row centered-form">--%>
        <%--<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">--%>
            <%--<div class="panel panel-default">--%>
                <%--<div class="panel-heading">--%>
                    <%--<h3 class="panel-title">Please sign up for Bootsnipp <small>It's free!</small></h3>--%>
                <%--</div>--%>
                <%--<div class="panel-body">--%>
                    <%--<form:form method="GET" commandName="player" action="/signup" role="form">--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-xs-6 col-sm-6 col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--<form:input path="name" type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="Login"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-xs-6 col-sm-6 col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--&lt;%&ndash;<input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">&ndash;%&gt;--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="form-group">--%>
                            <%--<form:input path="email" type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address"/>--%>
                        <%--</div>--%>

                        <%--<div class="row">--%>
                            <%--<div class="col-xs-6 col-sm-6 col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--<form:input path="password" type="password" name="password" id="password" class="form-control input-sm" placeholder="Password"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-xs-6 col-sm-6 col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--&lt;%&ndash;<input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">&ndash;%&gt;--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<input type="submit" value="Register" class="btn btn-info btn-block">--%>

                    <%--</form:form>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

    <%--<form action='/signup' method='GET'>--%>
        <%--<label for='nameReg'>Name</label>--%>
        <%--<input required type='text' name='nameReg'>--%>

        <%--<label for='emailReg'>Email</label>--%>
        <%--<input required type='email' name='emailReg'>--%>

        <%--<label for='passwordReg'>Password</label>--%>
        <%--<input required type='password' name='passwordReg'/>--%>
        <%--<input type='submit' name='action' value='Sign up' />--%>
    <%--</form>--%>
    <%--<form action="/login" method="get">--%>
        <%--<input type="submit" value="Main page"/>--%>
    <%--</form>--%>

</body>
</html>

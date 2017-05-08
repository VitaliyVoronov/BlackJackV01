<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/css/core.css" rel="stylesheet">
    <title>Registration</title>
</head>
<body>
<div class="container-table">
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
</body>
</html>

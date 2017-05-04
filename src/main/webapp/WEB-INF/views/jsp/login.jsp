<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
  <head>
      <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
      <link href="resources/css/core.css" rel="stylesheet" />
      <title>Login</title>
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
                              <a href="#" class="active" id="login-form-link1">Login</a>
                          </div>
                          <div class="col-xs-6">
                              <a href="/registrationForm" id="register-form-link1">Register</a>
                          </div>
                      </div>
                      <hr>
                  </div>
                  <div class="panel-body">
                      ${message}
                      <div class="row">
                          <div class="col-lg-12">
                              <form:form commandName="player" id="login-form" action="/checkPlayer" method="post" role="form" style="display: block;">
                                  <div class="form-group">
                                      <form:input path="name" type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value=""/>
                                  </div>
                                  <div class="form-group">
                                      <form:input path="password" type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"/>
                                  </div>
                                  <%--<div class="form-group text-center">--%>
                                      <%--<input type="checkbox" tabindex="3" class="" name="remember" id="remember">--%>
                                      <%--<label for="remember"> Remember Me</label>--%>
                                  <%--</div>--%>
                                  <div class="form-group">
                                      <div class="row">
                                          <div class="col-sm-6 col-sm-offset-3">
                                              <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                          </div>
                                      </div>
                                  </div>
                                  <%--<div class="form-group">--%>
                                      <%--<div class="row">--%>
                                          <%--<div class="col-lg-12">--%>
                                              <%--<div class="text-center">--%>
                                                  <%--<a href="/" tabindex="5" class="forgot-password">Forgot Password?</a>--%>
                                              <%--</div>--%>
                                          <%--</div>--%>
                                      <%--</div>--%>
                                  <%--</div>--%>
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

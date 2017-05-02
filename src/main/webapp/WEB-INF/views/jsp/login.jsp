<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
  <head>
      <%--<link href="<c:url value="/resources/css/loginAndRegistration.css"/>" rel="stylesheet">--%>
      <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
      <link href="resources/css/loginAndRegistration.css" rel="stylesheet">
      <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>--%>
      <%--<script src="resources/js/login.js"></script>--%>
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

  <%--<div class="container">--%>
      <%--<div class="row">--%>
          <%--<img src="/resources/images/blackjack2.png" id="blackjackimg">--%>
          <%--<div class="col-md-6 col-md-offset-3">--%>
              <%--<div class="panel panel-login">--%>
                  <%--<div class="panel-heading">--%>
                      <%--<div class="row">--%>
                          <%--<div class="col-xs-6">--%>
                              <%--<a href="#" class="active" id="login-form-link">Login</a>--%>
                          <%--</div>--%>
                          <%--<div class="col-xs-6">--%>
                              <%--<a href="#" id="register-form-link">Register</a>--%>
                          <%--</div>--%>
                      <%--</div>--%>
                      <%--<hr>--%>
                  <%--</div>--%>
                  <%--${message}--%>
                  <%--<div class="panel-body">--%>
                      <%--<div class="row">--%>
                          <%--<div class="col-lg-12">--%>
                              <%--<form:form commandName="player" id="login-form" action="/checkPlayer" method="post" role="form" style="display: block;">--%>
                                  <%--<div class="form-group">--%>
                                      <%--<form:input path="name" type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Login" value=""/>--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<form:input path="password" type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"/>--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group text-center">--%>
                                      <%--&lt;%&ndash;<input type="checkbox" tabindex="3" class="" name="remember" id="remember">&ndash;%&gt;--%>
                                      <%--&lt;%&ndash;<label for="remember"> Remember Me</label>&ndash;%&gt;--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<div class="row">--%>
                                          <%--<div class="col-sm-6 col-sm-offset-3">--%>
                                              <%--<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">--%>
                                          <%--</div>--%>
                                      <%--</div>--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<div class="row">--%>
                                          <%--<div class="col-lg-12">--%>
                                              <%--<div class="text-center">--%>
                                                  <%--&lt;%&ndash;<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>&ndash;%&gt;--%>
                                              <%--</div>--%>
                                          <%--</div>--%>
                                      <%--</div>--%>
                                  <%--</div>--%>
                              <%--</form:form>--%>
                              <%--<form id="register-form" action="/signUp" method="post" role="form" style="display: none;">--%>
                                  <%--<div class="form-group">--%>
                                      <%--<input  type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value=""/>--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">--%>
                                  <%--</div>--%>
                                  <%--<div class="form-group">--%>
                                      <%--<div class="row">--%>
                                          <%--<div class="col-sm-6 col-sm-offset-3">--%>
                                              <%--<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">--%>
                                          <%--</div>--%>
                                      <%--</div>--%>
                                  <%--</div>--%>
                              <%--</form>--%>
                          <%--</div>--%>
                      <%--</div>--%>
                  <%--</div>--%>
              <%--</div>--%>
          <%--</div>--%>
      <%--</div>--%>
  <%--</div>--%>
  <%--<div class="login">--%>
      <%--${message}--%>
      <%--<h1>Login</h1>--%>
        <%--<form:form method="GET" commandName="player" action="/checkPlayer" class="boxLogin" id="login">--%>
      <%--&lt;%&ndash;<form method="post">&ndash;%&gt;--%>
            <%--<form:input path="name" type="text" name="u" placeholder="Username" required="required" />--%>
          <%--&lt;%&ndash;<input type="text" name="u" placeholder="Username" required="required" />&ndash;%&gt;--%>
            <%--<form:input path="password" type="password" name="p" placeholder="Password" required="required"/>--%>
          <%--&lt;%&ndash;<input type="password" name="p" placeholder="Password" required="required" />&ndash;%&gt;--%>
          <%--<button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>--%>
        <%--</form:form>--%>
      <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
      <%--<br>--%>
      <%--<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>--%>
      <%--<!-- login bootsnipp -->--%>
      <%--<ins class="adsbygoogle"--%>
           <%--style="display:block"--%>
           <%--data-ad-client="ca-pub-9155049400353686"--%>
           <%--data-ad-slot="9589048256"--%>
           <%--data-ad-format="auto"></ins>--%>
      <%--<script>--%>
          <%--(adsbygoogle = window.adsbygoogle || []).push({});--%>
      <%--</script>--%>
  <%--</div>--%>
  <%--<div class="center">--%>

      <%--<form:form method="GET" commandName="player" action="/login" class="boxLogin" id="login">--%>

          <%--<fieldset>--%>

              <%--<form:label path="name">Name</form:label>--%>
              <%--<form:input path="name" type="text" placeholder="Username" />--%>

              <%--<form:label path="password" >Password</form:label>--%>
              <%--<form:input path="password" type="password" placeholder="Password"/>--%>

          <%--</fieldset>--%>

          <%--<fieldset id="actions">--%>
              <%--<input type="submit" id="submit" value="Log in">--%>
              <%--<a href="">Forgot your password?</a><a href="">Register</a>--%>
          <%--</fieldset>--%>

      <%--</form:form>--%>

          <%--&lt;%&ndash;<form action='/signIn' method='get'>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<label for='name'>Name</label>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<input required type='text' name='name'>&ndash;%&gt;--%>

              <%--&lt;%&ndash;<label for='password'>Password</label>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<input required type='password' name='password'>&ndash;%&gt;--%>

              <%--&lt;%&ndash;<input type='submit' value='Sign in' name="action"/>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<form action='/registration' method='get'>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<input type='submit' value='Registration' name='action'/>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<form action='/settings' method='get'>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<input type='submit' value='Settings' name='action'/>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
          <%--&lt;%&ndash;<form action='/game' method='get'>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<input type='submit' value='StartGame' name='action'/>&ndash;%&gt;--%>
          <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
<%--</div>--%>
  </body>
</html>

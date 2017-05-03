<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 4/21/17
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="resources/css/core.css" rel="stylesheet" >
    <title>Menu</title>
</head>
<body>
<div class="container">
    <div class="row">
        <img src="/resources/images/blackjack2.png" id="blackjackimg">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <h2>Menu</h2>
                    </div>
                    <hr>
                </div>
                Hello ${player.name}, email: ${player.email}
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <a href="/settings" type="button" name="settings" id="settings" class="form-control btn btn-login">Settings</a>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <a href="/game" type="button" name="startGame" id="startGame" tabindex="2" class="form-control btn btn-login">Start game</a>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6 col-sm-offset-3">
                                        <a href="/" type="button" name="logOut" id="login-submit" tabindex="4" class="form-control btn btn-login">Log out</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<p>Hello ${player.name}</p>--%>
<%--<p>Your email: ${player.email}</p>--%>
    <%--<form action='/settings' method='get'>--%>
        <%--<input type='submit' value='Settings' name='action'/>--%>
    <%--</form>--%>
    <%--<form action='/game' method='get'>--%>
        <%--<input type='submit' value='StartGame' name='action'/>--%>
    <%--</form>--%>
    <%--<form action='/' method='get'>--%>
        <%--<input type='submit' value='Sign out' name='action'/>--%>
    <%--</form>--%>
</body>
</html>

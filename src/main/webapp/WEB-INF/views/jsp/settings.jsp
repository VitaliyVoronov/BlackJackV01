<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="resources/css/core.css" rel="stylesheet" >
    <title>Settings</title>
    <style>
        .center {
            width: 100%;
            padding: 5%;
            margin: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <img src="/resources/images/blackjack2.png" id="blackjackimg">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <h2>Settings</h2>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    ${message}
                    <div class="row">
                        <div class="col-lg-12">
                            <form:form commandName="settings" id="register-form" action="/saveSettings" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <label for="sel1">Select number of decks in shoes:</label>
                                    <form:select path="decks" class="form-control" id="sel1">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <label for="sel1">Select minimum bet:</label>
                                    <form:select path="minBet" class="form-control" id="sel1">
                                        <option>1</option>
                                        <option>5</option>
                                        <option>10</option>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <label for="sel1">Select maximum bet:</label>
                                    <form:select path="maxBet" class="form-control" id="sel1">
                                        <option>15</option>
                                        <option>20</option>
                                        <option>30</option>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <label for="sel1">Select your money:</label>
                                    <form:select path="money" class="form-control" id="sel1">
                                        <option>50</option>
                                        <option>100</option>
                                        <option>200</option>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Save settings">
                                        </div>
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <a href="/menu" type="submit" tabindex="4" class="form-control btn btn-register">Main menu</a>
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


<%--<div class="center">--%>
    <%--<form action='/saveSettings' method='get'>--%>
        <%--<label for='decks'>Number of decks</label>--%>
        <%--<input required type='number' name='decks'>--%>

        <%--<label for='minBet'>Min bet</label>--%>
        <%--<input required type='number' name="minBet">--%>

        <%--<label for='maxBet'>Max bet</label>--%>
        <%--<input required type='number' name="maxBet">--%>

        <%--<label for='moneySet'>Money</label>--%>
        <%--<input required type='number' name='moneySet'>--%>

        <%--<input type='submit' value='Save settings' name='action' />--%>
    <%--</form>--%>
    <%--<form action="/login" method="get">--%>
        <%--<input type="submit" value="Main page"/>--%>
    <%--</form>--%>
<%--</div>--%>
</body>
</html>

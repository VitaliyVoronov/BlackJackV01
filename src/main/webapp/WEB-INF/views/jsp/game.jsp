<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 5/4/17
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="tag" %>

<html>
<head>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="/resources/css/gamehtml.css" type="text/css" rel="stylesheet">
    <title>Game</title>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="message-block">
            <h4>${message}</h4>
        </div>
        <div class="medium-block"></div>
        <div class="player-info-block">
            <h4>Player: ${namePlayer}</h4>
        </div>
    </div>
    <div class="container-table">
        <div class="table">
            <div class="side-block">
                <div class="inner-side-block" ></div>
                <div class="inner-side-block" id="bet-block">
                    <h4>Bet: ${bet}</h4>
                </div>
                <div class="inner-side-block" >
                    <h5>Min bet: ${settings.minBet}</h5>
                    <h5>Max bet: ${settings.maxBet}</h5>
                </div>
                <div class="inner-side-block" id="chips-block">
                    <a href="1" data-toggle="modal" data-target="#myModal" class="btn btn-circle btn-primary">1$</a>
                    <a href="5" data-toggle="modal" data-target="#myModal" class="btn btn-circle btn-primary">5$</a>
                    <a href="10" data-toggle="modal" data-target="#myModal" class="btn btn-circle btn-primary">10$</a>
                </div>
            </div>
            <div class="game-part">
                <div class="player" id="dealer">
                    <%--${nameDealer}--%>
                    <c:forEach var="card" items="${cardsDealer}">
                        <img src='${card}' alt='card' />
                    </c:forEach>
                    ${sumNumbersDealer}
                </div>
                <div class="player" id="player">
                    <%--${namePlayer}--%>
                    <c:forEach var="card" items="${cardsPlayer}">
                        <img src='${card}' alt='card' />
                    </c:forEach>
                    ${sumNumbersPlayer}
                </div>

            </div>
            <div class="side-block">
                <div class="right-inner-side-block" id="shoes-block">
                    <c:forEach var="shirt" items="${shirts}">
                        <img src='${shirt}' alt='card' class='shirt' /></p>
                    </c:forEach>
                </div>
                <div class="right-inner-side-block" id="balance-block">
                    <h4>Your balance: ${moneyPlayer}</h4>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="inner-footer">
            <div class="footer-side-block" >
            </div>
            <div class="footer-average-block" >
                <div class="col-xs-4" id="deal">
                    <a href="deal" role="button" class="btn btn-primary btn-block">DEAL</a>
                    <%--<form method="get" action="/game">--%>
                        <%--<input  name="action" type="submit" class="btn btn-primary btn-block" value="DEAL" >--%>
                    <%--</form>--%>
                </div>
                <div class="col-xs-4" id="hit">
                    <a href="hit" role="button" class="btn btn-primary btn-block">HIT</a>
                    <%--<form method="get" action="/game">--%>
                        <%--<input name="action" type="submit" class="btn btn-primary btn-block" value="HIT" >--%>
                    <%--</form>--%>
                </div>
                <div class="col-xs-4" id="stand">
                    <a href="stand" role="button" class="btn btn-primary btn-block">STAND</a>
                    <%--<form method="get" action="/game">--%>
                        <%--<input  name="action" type="submit" class="btn btn-primary btn-block " value="STAND" >--%>
                    <%--</form>--%>
                </div>
                <div class="col-xs-4" id="new-game">
                    <a href="newGame" role="button" class="btn btn-primary btn-block">New game</a>
                    <%--<form method="get" action="/game">--%>
                        <%--<input  name="action" type="submit" class="btn btn-primary btn-block" value="New game" >--%>
                    <%--</form>--%>
                </div>
            </div>
            <div class="footer-side-block" >
                <form method="get" action="/menu">
                    <input  name="action" type="submit" class="btn btn-primary center-block" value="Main menu" >
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

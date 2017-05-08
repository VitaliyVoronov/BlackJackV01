<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="resources/css/game.css" type="text/css" rel="stylesheet">
    <title>BlackJack</title>
</head>
<body>
    <div id="border">
        <div id="header">
            Your money: ${money}
                <h3>Your bet: ${bet}</h3>
        </div>
        <div id="shoes">

            <c:forEach var="shirt" items="${shirts}">
                <img src='${shirt}' alt='card' class='shoes' /></p>
            </c:forEach>

        </div>
        <div id="players">
        <div id="dealerHand">
            ${nameDealer}
            <c:forEach var="card" items="${cardsDealer}">
                <img src='${card}' alt='card' />
            </c:forEach>
            ${sumNumbersDealer}
        </div>
        <div id="playerHand">

            ${namePlayer}
            <c:forEach var="card" items="${cardsPlayer}">
                <img src='${card}' alt='card' />
            </c:forEach>
            ${sumNumbersPlayer}

        </div>
        </div>
        <div id="menu">
            <form method="get" action="/game">
                <input name="action" type="submit" class="btn btn-primary" value="HIT" >
            </form>
            <form method="get" action="/game">
                <input  name="action" type="submit" class="btn btn-primary" value="STAND" >
            </form>
            <form method="get" action="/game">
                <input  name="bet" type="submit" class="btn btn-primary" value="1" >
            </form>
            <form method="get" action="/game">
                <input  name="bet" type="submit" class="btn btn-primary" value="5" >
            </form>
            <form method="get" action="/game">
                <input  name="bet" type="submit" class="btn btn-primary" value="10" >
            </form>
            <form method="get" action="/game">
                <input  name="action" type="submit" class="btn btn-primary" value="DEAL" >
            </form>
            <form method="get" action="/game">
                <input  name="action" type="submit" class="btn btn-primary " value="New game" >
            </form>
            <form method="get" action="/menu">
                <input  name="action" type="submit" class="btn btn-primary" value="Main menu" >
            </form>
            Player's settings: ${playerSettings}

        </div>

        <div id="footer">
            ${message}
        </div>
    </div>

    </body>
</html>
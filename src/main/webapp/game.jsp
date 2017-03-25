<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>BlackJack</title>
    <link href="styleGame.css" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div id="border">
        <div id="header">
            Your money: ${money}
            <%--<%--%>
                <%--PrintWriter myOut = response.getWriter();--%>
                <%--Engine con = (Engine) request.getSession().getAttribute("engine");--%>
                <%--String query = ""+request.getQueryString();--%>

                <%--Player player = con.getPlayer();--%>
                <%--Player dealer = con.getDealer();--%>
            <%--%>--%>
                <%--if (query.equals("action=DEAL") && con.isContinuePushed()){--%>
                    <%--con.firstDealToAll();--%>
                    <%--if (player.getHand().getCards().size() > 0 && dealer.getHand().getCards().size() > 0){--%>
                        <%--con.setContinuePushed(false);--%>
                        <%--con.setGameTrue();--%>
                    <%--}--%>
                <%--}--%>

                <%--if(query.equals("action=CONTINUE") && !con.isGame()){--%>
                    <%--con.setContinuePushed(true);--%>
                    <%--con.clearTable();--%>
                    <%--con.countWin();--%>
                    <%--con.clearBet();--%>
                    <%--con.clearPoints();--%>
                <%--}--%>

                <%--if (query.equals("bet=1") && !con.isDealPushed() && con.isContinuePushed()){--%>
                    <%--con.takeMoneyFromPlayerToBet(1);--%>
                <%--}--%>
                <%--if (query.equals("bet=5") && !con.isDealPushed() && con.isContinuePushed()){--%>
                    <%--con.takeMoneyFromPlayerToBet(5);--%>

                <%--}--%>
                <%--if (query.equals("bet=10") && !con.isDealPushed() && con.isContinuePushed()){--%>
                    <%--con.takeMoneyFromPlayerToBet(10);--%>
                <%--}--%>
                <%--myOut.println("Your bet: "+con.getBet());--%>
            <%--%>--%>
                <%--<c:set var="bet" value="bet"/>--%>
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
            ${moneyPlayer}
            <c:forEach var="card" items="${cardsPlayer}">
                <img src='${card}' alt='card' />
            </c:forEach>
            ${sumNumbersPlayer}

        </div>
        </div>
        <div id="menu">
            <form method="get" action="/game">
                <input  name="action" type="submit" value="HIT" >
            </form>
            <form method="get" action="/game">
                <input  name="action" type="submit" value="STAND" >
            </form>
            <form method="get" action="/game">
                <input  name="bet" type="submit" value="1" >
            </form>
            <form method="get" action="/game">
                <input  name="bet" type="submit" value="5" >
            </form>
            <form method="get" action="/game">
                <input  name="bet" type="submit" value="10" >
            </form>
            <form method="get" action="/game">
                <input  name="action" type="submit" value="DEAL" >
            </form>
            <form method="get" action="/game">
                <input  name="action" type="submit" value="CONTINUE" >
            </form>
            <form method="get" action="login.jsp">
                <input  name="action" type="submit" value="Main menu" >
            </form>

        </div>

        <div id="footer">
            ${message}

        </div>
        </div>

    </body>
</html>
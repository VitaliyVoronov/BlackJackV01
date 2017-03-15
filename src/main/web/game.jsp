<%@ page import="ua.blackjack.controller.Controller" %>
<%@ page import="ua.blackjack.model.Card" %>
<%@ page import="ua.blackjack.model.Player" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 03.11.2016
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>BlackJack</title>
    <link href="styleGame.css" type="text/css" rel="stylesheet"/>
</head>
    <body>
    <div id="border">
        <div id="header">
            <%
                Controller con = (Controller) request.getSession().getAttribute("controller");
                String query = ""+request.getQueryString();

                Player player = con.getPlayer();
                Player dealer = con.getDealer();

                if (query.equals("action=DEAL") && con.isContinuePushed()){
                    con.firstDealToAll();
                    if (player.getHand().getCards().size() > 0 && dealer.getHand().getCards().size() > 0){
                        con.setContinuePushed(false);
                        con.setGameTrue();
                    }
                }

                if(query.equals("action=CONTINUE") && !con.isGame()){
                    con.setContinuePushed(true);
                    con.clearTable();
                    con.countWin();
                    con.clearBet();
                    con.clearPoints();
                }

                if (query.equals("bet=1") && !con.isDealPushed() && con.isContinuePushed()){
                    con.takeMoneyFromPlayerToBet(1);
                }
                if (query.equals("bet=5") && !con.isDealPushed() && con.isContinuePushed()){
                    con.takeMoneyFromPlayerToBet(5);

                }
                if (query.equals("bet=10") && !con.isDealPushed() && con.isContinuePushed()){
                    con.takeMoneyFromPlayerToBet(10);
                }
                out.println("Your bet: "+con.getBet());

            %>
        </div>
        <div id="shoes">
            <%
                    for (int i = 0; i < con.getShoes().size(); i++) {
                        Card c = con.getShoes().get(i);
                        out.println("<img src='" + c.getShirt() + "' alt='card' class='shoes'' />");
                    }
            %>
        </div>
        <div id="players">
        <div id="dealerHand">
            <%
                out.println(dealer.getName());
                if(query.equals("action=STAND") && con.isGame()){
                    con.dealCardsToDealer();
                    con.setGameFalse();
                }
                if (con.isGame() || !con.isContinuePushed()) {
                    for (int i = 0; i < dealer.getHand().getCards().size(); i++) {
                        Card c = dealer.getHand().getCards().get(i);
                        out.println("<img src='" + c.getFace() + "' alt='card' />");
                    }
                    out.println(dealer.getSumNumbers());
                }

            %>
        </div>
        <div id="playerHand">

            <%
                out.println(player.getName()+"</br>");
                out.println(player.getMoney());
                if (query.equals("action=HIT")) {
                    con.dealOneCardToPlayer();
                }
                if (con.isGame() || !con.isContinuePushed()) {
                    for (int i = 0; i < player.getHand().getCards().size(); i++) {
                        Card c = player.getHand().getCards().get(i);
                        out.println("<img src='" + c.getFace() + "' alt='card' />");
                    }
                    out.println(player.getSumNumbers());
                }

            %>
        </div>
        </div>
        <div id="menu">
            <form method="get" action="">
                <input  name="action" type="submit" value="HIT" >
            </form>
            <form method="get" action="">
                <input  name="action" type="submit" value="STAND" >
            </form>
            <form method="get" action="">
                <input  name="bet" type="submit" value="1" >
            </form>
            <form method="get" action="">
                <input  name="bet" type="submit" value="5" >
            </form>
            <form method="get" action="">
                <input  name="bet" type="submit" value="10" >
            </form>
            <form method="get" action="">
                <input  name="action" type="submit" value="DEAL" >
            </form>
            <form method="get" action="">
                <input  name="action" type="submit" value="CONTINUE" >
            </form>
            <form method="get" action="index.jsp">
                <input  name="action" type="submit" value="Main menu" >
            </form>

        </div>

        <div id="footer">
            <%out.println(con.getMassage());%>

        </div>
        </div>

    </body>
</html>
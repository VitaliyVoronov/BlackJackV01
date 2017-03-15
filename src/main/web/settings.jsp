<%@ page import="ua.blackjack.controller.Controller" %><%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 09.11.2016
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
<div class="center">
    <form action='main.jsp' method='get'>
        <label for=' decks'>Number of decks</label>
        <input required type='number' name='decks'>

        <label for='minBet'>Min bet</label>
        <input required type='number' name="minBet">

        <label for='maxBet'>Max bet</label>
        <input required type='number' name="maxBet">

        <label for='moneySet'>Money</label>
        <input required type='number' name='moneySet'>

        <input type='submit' value='Save settings' name='action' />
    </form>
    <form action="index.jsp" method="get">
        <input type="submit" value="Main page"/>
    </form>
</div>
</body>
</html>

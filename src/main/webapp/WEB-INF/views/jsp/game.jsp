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
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
    <link href="resources/css/gamehtml.css" type="text/css" rel="stylesheet">
    <title>Game</title>
</head>
<body>
<div class="header">
    <div class="message-block">
    </div>
    <div class="medium-block"></div>
    <div class="player-info-block">
    </div>
</div>
<div class="container">
    <div class="table">
        <div class="left-side-block">
            <div class="inner-side-block" id="shoes">
            </div>
            <div class="inner-side-block" id="balance">
            </div>
        </div>
        <div class="game-part">
            <div class="player" id="dealer">
            </div>
            <div class="player" id="player">
            </div>

        </div>
        <div class="right-side-block">
            <div class="inner-side-block" id="chips">

            </div>
            <div class="inner-side-block" id="bet-block">
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="inner-footer">
        <div class="footer-side-block" >
        </div>
        <div class="footer-average-block" >
            <div class="button" id="deal">
                <form method="get" action="/game">
                    <input  name="action" type="submit" class="btn btn-primary btn-block" value="DEAL" >
                </form>
            </div>
            <div class="button" id="hit">
                <form method="get" action="/game">
                    <input name="action" type="submit" class="btn btn-primary btn-block" value="HIT" >
                </form>
            </div>
            <div class="button" id="stand">
                <form method="get" action="/game">
                    <input  name="action" type="submit" class="btn btn-primary btn-block " value="STAND" >
                </form>
            </div>
            <div class="button" id="new-game">
                <form method="get" action="/game">
                    <input  name="action" type="submit" class="btn btn-primary btn-block" value="New game" >
                </form>
            </div>
        </div>
        <div class="footer-side-block" >
            <form method="get" action="/menu">
                <input  name="action" type="submit" class="btn btn-primary center-block" value="Main menu" >
            </form>
        </div>
    </div>
</div>

</body>
</html>

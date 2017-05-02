<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 4/21/17
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blackjack</title>
</head>
<body>
<p>Hello ${player.name}</p>
<p>Your email: ${player.email}</p>
    <form action='/settings' method='get'>
        <input type='submit' value='Settings' name='action'/>
    </form>
    <form action='/game' method='get'>
        <input type='submit' value='StartGame' name='action'/>
    </form>
</body>
</html>

<%@ page import="ua.blackjack.controller.Controller" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 08.11.2016
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Main</title>
</head>
<body>
    <%
        Controller con = (Controller) request.getSession().getAttribute("controller");
        String query = request.getParameter("action");


//        if (query.equals("Registration")) {
//            String redirectURL = "registration.jsp";
//            response.sendRedirect(redirectURL);

        if (query.equals("Sign up")){
                if (con.isAvailableName(request.getParameter("nameReg"))) {
                    con.addPlayerToDB(request.getParameter("nameReg"), request.getParameter("passwordReg"), request.getParameter("emailReg"));
                    String redirectURL = "index.jsp";
                    response.sendRedirect(redirectURL);
                } else {
                    request.getSession().setAttribute("massageReg", "Something wrong! Fill in all the fields!");
                    String redirectURL = "registration.jsp";
                    response.sendRedirect(redirectURL);
                }
        } else if (query.equals("Settings")  && con.isEnter()) {
            String redirectURL = "settings.jsp";
            response.sendRedirect(redirectURL);
        } else if (query.equals("Save settings")) {
                int decks = Integer.parseInt(request.getParameter("decks"));
                int minBet = Integer.parseInt(request.getParameter("minBet"));
                int maxBet = Integer.parseInt(request.getParameter("maxBet"));
                int money = Integer.parseInt(request.getParameter("moneySet"));
            con.getPlayer().setSettingsParameters(decks,minBet,maxBet,money);
            con.writeSettingsToXml();
            String redirectURL = "settings.jsp";
            response.sendRedirect(redirectURL);

        } else if(query.equals("New game") && con.isEnter()) {
            con.newGame();
            String redirectURL = "game.jsp";
            response.sendRedirect(redirectURL);
        } else {
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
        }
    %>

</body>
</html>

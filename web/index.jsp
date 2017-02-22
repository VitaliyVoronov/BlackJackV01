<%@ page import="ua.blackjack.controller.Controller" %>
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
      <style>
          .center {
              width: 40%;
              height: 50%;
              padding: 5%;
              margin: auto;
          }
      </style>
  </head>
  <body>
  <div class="center">
      <%
          String query = request.getParameter("action");
              if (query != null) {

              if (query.equals("Enter")) {
                  Controller con = new Controller();
                  request.getSession().setAttribute("controller", con);

                  if (con.checkNameAndPassword(request.getParameter("name"), request.getParameter("password"))) {
                      con.createPlayerFromDB(request.getParameter("name"));

                      con.setEnter(true);
                      String massage = "Hi " + con.getPlayer().getName()+"; Email: "+con.getPlayer().getEmail();
                      request.getSession().setAttribute("massage", massage);
                      //con.newGame();
                      String redirectURL = "index.jsp";
                      response.sendRedirect(redirectURL);
                  } else {
                      String massage = "Incorrect login or password";
                      request.getSession().setAttribute("massage", massage);
                      String redirectURL = "index.jsp";
                      response.sendRedirect(redirectURL);
                  }
                  request.getSession().setAttribute("controller", con);
              }
          }

          if((String)request.getSession().getAttribute("massage") != null){
              out.println(request.getSession().getAttribute("massage"));
          }

      %>

          <form action='' method='get'>
              <label for='name'>Name</label>
              <input required type='text' name='name'>

              <label for='password'>Password</label>
              <input required type='password' name='password'>

              <input type='submit' value='Enter' name="action"/>
          </form>
          <form action='/registration.jsp' method='post'>
              <input type='submit' value='Registration' name='action'/>
          </form>
          <form action='main.jsp' method='get'>
              <input type='submit' value='Settings' name='action'/>
          </form>
          <form action='main.jsp' method='get'>
              <input type='submit' value='New game' name='action'/>
          </form>
</div>
  </body>
</html>

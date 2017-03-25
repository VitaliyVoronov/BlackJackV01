<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  ${message}
  <div class="center">

      <%--<%--%>
          <%--String query = request.getParameter("action");--%>
              <%--if (query != null) {--%>

              <%--if (query.equals("Enter")) {--%>
                  <%--Engine con = new Engine();--%>
                  <%--request.getSession().setAttribute("engine", con);--%>

                  <%--if (con.checkNameAndPassword(request.getParameter("name"), request.getParameter("password"))) {--%>
                      <%--con.getPlayerFromDB(request.getParameter("name"));--%>

                      <%--con.setEnter(true);--%>
                      <%--String massage = "Hi " + con.getPlayer().getName()+"; Email: "+con.getPlayer().getEmail();--%>
                      <%--request.getSession().setAttribute("massage", massage);--%>
                      <%--String redirectURL = "login.jsp";--%>
                      <%--response.sendRedirect(redirectURL);--%>
                  <%--} else {--%>
                      <%--String massage = "Incorrect login or password";--%>
                      <%--request.getSession().setAttribute("massage", massage);--%>
                      <%--String redirectURL = "login.jsp";--%>
                      <%--response.sendRedirect(redirectURL);--%>
                  <%--}--%>
                  <%--request.getSession().setAttribute("engine", con);--%>
              <%--}--%>
          <%--}--%>
      <%--%>--%>

          <form action='/enter' method='get'>
              <label for='name'>Name</label>
              <input required type='text' name='name'>

              <label for='password'>Password</label>
              <input required type='password' name='password'>

              <input type='submit' value='Enter' name="action"/>
          </form>
          <form action='/registration' method='get'>
              <input type='submit' value='Registration' name='action'/>
          </form>
          <form action='/settings' method='get'>
              <input type='submit' value='Settings' name='action'/>
          </form>
          <form action='/game' method='get'>
              <input type='submit' value='New game' name='action'/>
          </form>
</div>
  </body>
</html>

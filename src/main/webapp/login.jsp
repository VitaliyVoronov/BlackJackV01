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
              <input type='submit' value='NewGame' name='action'/>
          </form>
</div>
  </body>
</html>

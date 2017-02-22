<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 09.11.2016
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Registration</title>

</head>
<body>
    <%
        if((String)request.getSession().getAttribute("massageReg") != null){
            out.println(request.getSession().getAttribute("massageReg"));
        }
    %>
    <form action='/registration' method='POST'>
        <label for='nameReg'>Name</label>
        <input required type='text' name='nameReg'>

        <label for='emailReg'>Email</label>
        <input required type='email' name='emailReg'>

        <label for='passwordReg'>Password</label>
        <input required type='password' name='passwordReg'/>
        <input type='submit' name='action' value='Sign up' />
    </form>
    <form action="/main" method="get">
        <input type="submit" value="Main page"/>
    </form>

</body>
</html>

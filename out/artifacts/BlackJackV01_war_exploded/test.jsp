<%@ page import="ua.blackjack.controller.MainServlet" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 14.11.2016
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TEST</title>
</head>
<body>
    <%
        MainServlet ser = new MainServlet();
        ArrayList l = (ArrayList) request.getSession().getAttribute("list");
        for(int i = 0; i < l.size(); i++){
            out.write(""+l.get(i));
        }
    %>
</body>
</html>

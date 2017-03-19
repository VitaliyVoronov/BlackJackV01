<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration form</title>

</head>
<body>
    ${message}
    <form action='/signup' method='GET'>
        <label for='nameReg'>Name</label>
        <input required type='text' name='nameReg'>

        <label for='emailReg'>Email</label>
        <input required type='email' name='emailReg'>

        <label for='passwordReg'>Password</label>
        <input required type='password' name='passwordReg'/>
        <input type='submit' name='action' value='Sign up' />
    </form>
    <form action="/login" method="get">
        <input type="submit" value="Main page"/>
    </form>

</body>
</html>

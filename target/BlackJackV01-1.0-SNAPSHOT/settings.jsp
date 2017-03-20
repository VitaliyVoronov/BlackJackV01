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
${message}
<div class="center">
    <form action='/saveSettings' method='get'>
        <label for='decks'>Number of decks</label>
        <input required type='number' name='decks'>

        <label for='minBet'>Min bet</label>
        <input required type='number' name="minBet">

        <label for='maxBet'>Max bet</label>
        <input required type='number' name="maxBet">

        <label for='moneySet'>Money</label>
        <input required type='number' name='moneySet'>

        <input type='submit' value='Save settings' name='action' />
    </form>
    <form action="/login" method="get">
        <input type="submit" value="Main page"/>
    </form>
</div>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1><hr>
<h3>Регістрація</h3>

<form action="/registration" method="post">
    Ім'я користувача: <input type="text" name="name"><br><br>
    Email: <input type="email" name="email"><br><br>
    Номер телефону: <input type="text" name="phoneNumber"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зареєструватись">
</form>
<#if errorMassage??>
    <h2 style="color: crimson">${errorMassage}</h2>
</#if>
</body>
</html>

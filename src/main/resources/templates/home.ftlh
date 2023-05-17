<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1>
<hr>
<#if user.email??>
    <h3>Ім'я користувача: ${user.name}</h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Вийти">
    </form>
    <br>
    <a href="/user-account">Особистий кабінет</a>
    <#if user.isAdmin()>
        <a href="/admin">Панель адміністратора</a>
    </#if>
<#else>
    <a href="/login">Вхід</a>
</#if>

<hr>
<h3>Найкращі спеціалісти!</h3>
<form action="/" method="get">
    Знайдіть собі спеціаліста: <input type="text" name="specialization"><br>
    <input type="submit" value="Знайти">
</form>
<#list doctors as doctor>
    <div>
        <p>
            <b>${doctor.nameSurname}</b>
            ${doctor.specialization}
            <a href="/doctor/${doctor.id}">Записатись</a>
        </p>
    </div>
</#list>
</body>
</html>
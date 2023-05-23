<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1>
<h3>Панель адміністратора</h3>
<#if doctors??>
    <table border="1">
        <tr>
            <th><b>Ім'я та прізвище</b></th>
            <th><b>Спеціальність</b></th>
            <th><b>Номер телефону</b></th>
            <th><b>Редагування</b></th>
            <th><b>Видалення</b></th>
        </tr>
        <#list doctors as doctor>
            <tr>
                <th>${doctor.nameSurname}</th>
                <th>${doctor.specialization}</th>
                <th>${doctor.phoneNumber}</th>
                <th><a href="/doctor/edit/${doctor.id}">Редагувати</a></th>
                <th>
                    <form action="/doctor/remove/${doctor.id}" method="post">
                        <input type="hidden" name="_csrf" value=${_csrf.token}>
                        <input type="submit" value="Видалити">
                    </form>
                </th>
            </tr>
        </#list>
    </table>
<#else>
    <h3>Лікарів немає</h3>
</#if>
<hr>
<form action="/admin" method="get">
    Знайти користувача: <input type="text" name="name"><br>
    <input type="submit" value="Знайти">
</form>
<#if users??>
    <table border="1">
        <tr>
            <th><b>Ім'я та прізвище</b></th>
            <th><b>Email</b></th>
            <th><b>Статус</b></th>
            <th><b>Роль</b></th>
            <th><b>Бан</b></th>
            <th><b>Змінити роль</b></th>
            <th><b>Інформація</b></th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.name}</th>
                <th>${user.email}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role}</#list></th>
                <th>
                    <#if user.active>
                        <form action="/admin/user/ban/${user.id}" method="post">
                            <input type="hidden" name="_csrf" value=${_csrf.token}>
                            <input type="submit" value="Забанити">
                        </form>
                    <#else>
                        <form action="/admin/user/unban/${user.id}" method="post">
                            <input type="hidden" name="_csrf" value=${_csrf.token}>
                            <input type="submit" value="Розбанити">
                        </form>
                    </#if>
                </th>
                <th>
                    <form action="/admin/user/change-role/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value=${_csrf.token}>
                        <input type="submit" value="Змінити роль">
                    </form>
                </th>
                <th><a href="/admin/user/${user.id}">Детальніше</a></th>
            </tr>
        </#list>
    </table>
</#if>

<hr>
<h3>Додати лікаря</h3>
<form action="/doctor/add" method="post" enctype="multipart/form-data">
    Ім'я та прізвище: <input type="text" name="nameSurname"/><br><br>
    Спеціалізація: <input type="text" name="specialization"/><br><br>
    Номер телефону: <input type="text" name="phoneNumber"/><br><br>
    Фотографія: <input type="file" name="file"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Додати">
</form>
<hr>
<a href="/">На головну</a>
</body>
</html>
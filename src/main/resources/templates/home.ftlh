<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic | <a href="/login">Вхід</a></h1><hr>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вийти">
</form>
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
<hr>
<h3>Додати лікаря</h3>
<form action="/doctor/add" method="post" enctype="multipart/form-data">
    Ім'я та прізвище: <input type="text" name="nameSurname"/><br><br>
    Спеціалізація: <input type="text" name="specialization"/><br><br>
    Номер телефону: <input type="text" name="phoneNumber"/><br><br>
    Фотографія: <input type="file" name="file"/><br><br>
    <input type="submit" value="Додати">
</form>

</body>
</html>
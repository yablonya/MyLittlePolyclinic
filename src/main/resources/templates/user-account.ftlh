<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1>
<h2>Особистий кабінет</h2>
<hr>
<h3>Особиста інформація</h3>
<b>Ім'я та прізвище:</b> ${user.name}<br><br>
<b>Email:</b> ${user.email}
<hr>
<h3>Мої записи</h3>
<#if user.admissions??>
    <table border="1">
        <tr>
            <th><b>Ім'я та прізвище</b></th>
            <th><b>Спеціальність</b></th>
            <th><b>Час запису</b></th>
            <th><b>Видалення запису</b></th>
        </tr>
        <#list user.admissions as admission>
            <#list doctors as doctor>
                <#if admission.doctor.id == doctor.id>
                    <tr>
                        <th><a href="/doctor/${doctor.id}">${doctor.nameSurname}</th>
                        <th>${doctor.specialization}</th>
                        <th>${admission.time}</th>
                        <th>
                            <form action="/user/remove-admission/${admission.id}" method="post">
                                <input type="hidden" name="_csrf" value=${_csrf.token}>
                                <input type="submit" value="Видалити">
                            </form>
                        </th>
                    </tr>
                </#if>
            </#list>
        <#else>
            <h4>Записів немає</h4>
        </#list>
    </table>
</#if>
<hr>
<a href="/">На головну</a>
</body>
</html>

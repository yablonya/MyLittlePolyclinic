<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1>
<h3>Особистий кабінет</h3>
<hr>
<h4>Мої записи</h4>
<#if user.admissions??>
    <table border="1">
        <tr>
            <th><b>Ім'я та прізвище</b></th>
            <th><b>Спеціальність</b></th>
            <th><b>Час запису</b></th>
            <th><b>Видалення запису</b></th>
        </tr>
        <#list user.admissions as admission>
            <tr>
                <th><a href="/doctor/${admission.doctor.id}">${admission.doctor.nameSurname}</th>
                <th>${admission.doctor.specialization}</th>
                <th>${admission.time}</th>
                <th>
                    <form action="/user/remove-admission/${admission.id}" method="post">
                        <input type="hidden" name="_csrf" value=${_csrf.token}>
                        <input type="submit" value="Видалити">
                    </form>
                </th>
            </tr>
        </#list>
    </table>
<#else>
    <h3>Записів немає</h3>
</#if>
<hr>
<a href="/">На головну</a>
</body>
</html>